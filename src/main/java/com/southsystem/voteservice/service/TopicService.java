package com.southsystem.voteservice.service;

import com.southsystem.voteservice.dto.request.TopicRequestDto;
import com.southsystem.voteservice.dto.response.TopicResponseDto;
import com.southsystem.voteservice.model.Topic;
import com.southsystem.voteservice.repository.TopicRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public TopicResponseDto save(TopicRequestDto topicRequestDto) {
        Topic topicSaved = topicRepository.save(topicRequestDto.toTopic());
        return new TopicResponseDto(topicSaved);
    }

    public void updateTopicAfterProduceResultVote(Topic topic) {
        topic.setSendResult(true);
        topicRepository.save(topic);
    }

    public List<Topic> findBySessionEndDateBeforeAndSendResultFalse() {
        return topicRepository.findBySessionEndDateBeforeAndSendResultFalse(LocalDateTime.now());
    }

    public Topic findById(Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (!topic.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return topic.get();
    }

}
