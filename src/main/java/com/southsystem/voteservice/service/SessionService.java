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
        setEndDate(sessionRequestDto);

        Session session = sessionRequestDto.toSession();
        sessionRepository.save(session);
        return new SessionResponseDto(session);
    }

    private void setEndDate(SessionRequestDto session) {
        Integer sessionTime = Objects.isNull(session.getSessionTimeInMinute()) ? 1 : session.getSessionTimeInMinute();
        session.setEndDate(session.getStartDate().plusMinutes(sessionTime));
    }

}
