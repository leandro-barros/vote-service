package com.southsystem.voteservice.dto.response;

import com.southsystem.voteservice.model.Session;
import com.southsystem.voteservice.model.Topic;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SessionResponseDto {

    private Topic topic;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String open;

    public SessionResponseDto(Session session) {
        this.topic = session.getTopic();
        this.startDate = session.getStartDate();
        this.endDate = session.getEndDate();
        this.open = session.getOpen() == true ? "Sim" : "Não";
    }

}
