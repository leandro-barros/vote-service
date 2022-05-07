package com.southsystem.voteservice.dto.request;

import com.southsystem.voteservice.model.Session;
import com.southsystem.voteservice.model.Topic;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
public class SessionRequestDto {

    @NotNull
    private Topic topic;

    @NotNull
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer sessionTimeInMinute;

    public Session toSession() {
        return new Session(null, topic, startDate, endDate, true);
    }

}
