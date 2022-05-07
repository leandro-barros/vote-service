package com.southsystem.voteservice.service;

import com.southsystem.voteservice.dto.request.SessionRequestDto;
import com.southsystem.voteservice.dto.response.SessionResponseDto;
import com.southsystem.voteservice.model.Session;
import com.southsystem.voteservice.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public SessionResponseDto openSession(SessionRequestDto sessionRequestDto) {
        Session session = sessionRequestDto.toSession();
        checkEndDate(session);
        sessionRepository.save(session);
        return new SessionResponseDto(session);
    }

    private void checkEndDate(Session session) {
        if (Objects.isNull(session.getEndDate())) {
            session.setEndDate(session.getStartDate().plusMinutes(1));
        }
    }

}
