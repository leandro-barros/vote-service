package com.southsystem.voteservice.service;

import com.southsystem.voteservice.dto.request.SessionRequestDto;
import com.southsystem.voteservice.dto.response.SessionResponseDto;
import com.southsystem.voteservice.model.Session;
import com.southsystem.voteservice.repository.SessionRepository;
import com.southsystem.voteservice.service.exception.SessionRegisteredException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final TopicService topicService;

    public SessionService(SessionRepository sessionRepository, TopicService topicService) {
        this.sessionRepository = sessionRepository;
        this.topicService = topicService;
    }

    public SessionResponseDto openSession(Long topicId, SessionRequestDto sessionRequestDto) {
        sessionRequestDto.setTopic(topicService.findById(topicId));
        existsSession(sessionRequestDto.getTopic().getId());
        setDates(sessionRequestDto);

        Session session = sessionRequestDto.toSession();
        sessionRepository.save(session);
        return new SessionResponseDto(session);
    }

    private void setDates(SessionRequestDto session) {
        session.setStartDate(LocalDateTime.now());
        Integer sessionTime = Objects.isNull(session.getSessionTimeInMinute()) ? 1 : session.getSessionTimeInMinute();
        session.setEndDate(session.getStartDate().plusMinutes(sessionTime));
    }

    private void existsSession(Long topicId) {
        if (sessionRepository.existsByTopicId(topicId)) {
            throw new SessionRegisteredException();
        }
    }

}
