package com.southsystem.voteservice.dto.request;

import com.southsystem.voteservice.model.Topic;
import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
=======
import javax.validation.constraints.NotBlank;

>>>>>>> fbf3a974dd247b760d1a96c223ba4e1cf9e8d39f
@Setter
@Getter
public class TopicRequestDto {

<<<<<<< HEAD
    private String subject;

    private String description;

    public Topic toTopic() {
        return new Topic(null, null, subject, description);
=======
    @NotBlank
    private String subject;

    @NotBlank
    private String description;

    public Topic toTopic() {
        return new Topic(null, null, subject, description, false);
>>>>>>> fbf3a974dd247b760d1a96c223ba4e1cf9e8d39f
    }

}
