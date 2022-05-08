package com.southsystem.voteservice.service;

import com.southsystem.voteservice.dto.request.VoteRequestDto;
import com.southsystem.voteservice.dto.response.VoteResponseDto;
import com.southsystem.voteservice.model.Session;
import com.southsystem.voteservice.model.Topic;
import com.southsystem.voteservice.model.Vote;
import com.southsystem.voteservice.repository.TopicRepository;
import com.southsystem.voteservice.repository.VoteRepository;
import com.southsystem.voteservice.service.exception.RegisteredVotedException;
import com.southsystem.voteservice.service.exception.SessionNotOpenException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    private final TopicRepository topicRepository;

    public VoteService(VoteRepository voteRepository, TopicRepository topicRepository) {
        this.voteRepository = voteRepository;
        this.topicRepository = topicRepository;
    }

    public VoteResponseDto saveVote(Long topicId, VoteRequestDto voteRequestDto) {
        Topic topic = findById(topicId);
        voteRequestDto.setTopic(topic);

        Vote vote = voteRequestDto.toVote();

        validations(vote);

        Vote voteSaved = voteRepository.save(vote);
        return new VoteResponseDto(voteSaved);
    }

    private Topic findById(Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (!topic.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return topic.get();
    }

    private void validations(Vote vote) {
        isSessionOpen(vote.getTopic().getSession());
        isRegisteredVoted(vote);
    }

    private void isSessionOpen(Session session) {
        if (Objects.isNull(session) || session.getEndDate().isBefore(LocalDateTime.now()) || !session.getOpen()) {
            throw new SessionNotOpenException();
        }
    }

    private void isRegisteredVoted(Vote vote) {
        if (voteRepository.existsByAssociateIdAndTopicId(vote.getAssociate().getId(), vote.getTopic().getId())) {
            throw new RegisteredVotedException();
        }
    }

}
