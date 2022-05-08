package com.southsystem.voteservice.service;

import com.southsystem.voteservice.dto.integration.DocumentDto;
import com.southsystem.voteservice.dto.request.VoteRequestDto;
import com.southsystem.voteservice.dto.response.VoteResponseDto;
import com.southsystem.voteservice.dto.response.VoteResultDto;
import com.southsystem.voteservice.integration.DocumentClient;
import com.southsystem.voteservice.model.Associate;
import com.southsystem.voteservice.model.Session;
import com.southsystem.voteservice.model.Topic;
import com.southsystem.voteservice.model.Vote;
import com.southsystem.voteservice.repository.VoteRepository;
import com.southsystem.voteservice.service.exception.InvalidCpfException;
import com.southsystem.voteservice.service.exception.RegisteredVotedException;
import com.southsystem.voteservice.service.exception.SessionNotOpenException;
import com.southsystem.voteservice.service.exception.UnableToVoteException;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    private final TopicService topicService;

    private final AssociateService associateService;

    private final DocumentClient documentClient;


    public VoteService(VoteRepository voteRepository, TopicService topicService, AssociateService associateService, DocumentClient documentClient) {
        this.voteRepository = voteRepository;
        this.topicService = topicService;
        this.documentClient = documentClient;
        this.associateService = associateService;
    }

    public VoteResponseDto saveVote(Long topicId, VoteRequestDto voteRequestDto) {
        Topic topic = topicService.findById(topicId);
        Associate associate = associateService.findById(voteRequestDto.getAssociate().getId());

        Vote vote = Vote.builder().associate(associate).topic(topic).vote(voteRequestDto.getVote()).build();

        validations(vote);

        Vote voteSaved = voteRepository.save(vote);
        return new VoteResponseDto(voteSaved);
    }

    public VoteResultDto result(Long topicId) {
        Topic topic = topicService.findById(topicId);

        if (isSessionOpen(topic.getSession())) {
            VoteResultDto voteResultDto = VoteResultDto.builder().voteInFavor(0L).voteAgainst(0L)
                                                   .topic(topic.getSubject()).result("Eleição está em andamento.")
                                          .build();
            return voteResultDto;
        }

        return resultVoted(topic);
    }

    public VoteResultDto resultVoted(Topic topic) {
        Long voteInFavor = voteRepository.countByTopicAndVoteTrue(topic);
        Long voteAgainst = voteRepository.countByTopicAndVoteFalse(topic);

        String result = checkResult(voteInFavor, voteAgainst);

        VoteResultDto voteResultDto = VoteResultDto.builder().voteInFavor(voteInFavor).voteAgainst(voteAgainst)
                                                                .topic(topic.getSubject()).result(result)
                                                            .build();
        return voteResultDto;
    }

    private String checkResult(Long voteInFavor, Long voteAgainst) {
        String result = null;
        if (voteAgainst == 0 && voteInFavor == 0) {
            result = "Não houve voto na pauta";
        } else if (voteInFavor == voteAgainst) {
            result = "Votação da Pauta ficou empate";
        } else {
            result = (voteInFavor > voteAgainst) ? "Pauta aprovada" : "Pauta reprovada";
        }

        return result;
    }

    private void validations(Vote vote) {
        if (!isSessionOpen(vote.getTopic().getSession())) {
            throw new SessionNotOpenException();
        }
        isRegisteredVoted(vote);
        checkPermissionToVoteByCpf(vote.getAssociate().getCpf());
    }

    private boolean isSessionOpen(Session session) {
        if (Objects.isNull(session) || session.getEndDate().isBefore(LocalDateTime.now())) {
            return false;
        }
        return true;
    }

    private void isRegisteredVoted(Vote vote) {
        if (voteRepository.existsByAssociateIdAndTopicId(vote.getAssociate().getId(), vote.getTopic().getId())) {
            throw new RegisteredVotedException();
        }
    }

    private void checkPermissionToVoteByCpf(String cpf) {
        try {
            ResponseEntity<DocumentDto> documentDto = documentClient.findByCpf(cpf);

            if (documentDto.getStatusCode().is4xxClientError()) {
                throw new InvalidCpfException();
            } else if(documentDto.getBody().getStatus().equalsIgnoreCase("UNABLE_TO_VOTE")) {
                throw new UnableToVoteException();
            }
        } catch (FeignException e) {
            throw new InvalidCpfException();
        }
    }

}
