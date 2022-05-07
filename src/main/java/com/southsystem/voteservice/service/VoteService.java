package com.southsystem.voteservice.service;

import com.southsystem.voteservice.dto.request.VoteRequestDto;
import com.southsystem.voteservice.dto.response.VoteResponseDto;
import com.southsystem.voteservice.model.Vote;
import com.southsystem.voteservice.repository.TopicRepository;
import com.southsystem.voteservice.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    private final TopicRepository topicRepository;

    public VoteService(VoteRepository voteRepository, TopicRepository topicRepository) {
        this.voteRepository = voteRepository;
        this.topicRepository = topicRepository;
    }

    public VoteResponseDto saveVote(Long topicId, VoteRequestDto voteRequestDto) {
        voteRequestDto.setTopic(topicRepository.findById(topicId).get());

        Vote voteSaved = voteRepository.save(voteRequestDto.toVote());
        return new VoteResponseDto(voteSaved);
    }

}
