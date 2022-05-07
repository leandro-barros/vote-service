package com.southsystem.voteservice.dto.request;

import com.southsystem.voteservice.model.Topic;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class TopicRequestDto {

    @NotBlank
    private String subject;

    @NotBlank
    private String description;

    public Topic toTopic() {
        return new Topic(null, null, subject, description);
    }

}
