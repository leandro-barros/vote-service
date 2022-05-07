package com.southsystem.voteservice;

import com.southsystem.voteservice.dto.request.TopicRequestDto;
import com.southsystem.voteservice.dto.response.TopicResponseDto;
import com.southsystem.voteservice.model.Topic;
import com.southsystem.voteservice.repository.TopicRepository;
import org.springframework.stereotype.Service;

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

}
