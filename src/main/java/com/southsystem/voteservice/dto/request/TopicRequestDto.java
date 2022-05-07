package com.southsystem.voteservice.dto.request;

import com.southsystem.voteservice.model.Topic;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TopicRequestDto {

    private String subject;

    private String description;

    public Topic toTopic() {
        return new Topic(null, null, subject, description);
    }

}
