package com.southsystem.voteservice.dto.response;

import com.southsystem.voteservice.model.Topic;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TopicResponseDto {

    private Long id;

    private String subject;

    private String description;

    public TopicResponseDto(Topic topic) {
        this.id = topic.getId();
        this.subject = topic.getSubject();
        this.description = topic.getDescription();
    }

}
