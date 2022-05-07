package com.southsystem.voteservice.dto.request;

import com.southsystem.voteservice.model.Session;
import com.southsystem.voteservice.model.Topic;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SessionRequestDto {

    private Topic topic;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public Session toSession() {
        return new Session(null, topic, startDate, endDate, true);
    }

}
