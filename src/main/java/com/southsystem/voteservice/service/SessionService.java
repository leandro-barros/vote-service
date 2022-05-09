package com.southsystem.voteservice.service;

import com.southsystem.voteservice.dto.request.SessionRequestDto;
import com.southsystem.voteservice.dto.response.SessionResponseDto;
import com.southsystem.voteservice.model.Session;
import com.southsystem.voteservice.repository.SessionRepository;
import com.southsystem.voteservice.service.exception.SessionRegisteredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final TopicService topicService;

    public SessionService(SessionRepository sessionRepository, TopicService topicService) {
        this.sessionRepository = sessionRepository;
        this.topicService = topicService;
    }

    public SessionResponseDto openSession(Long topicId, SessionRequestDto sessionRequestDto) {
        Session session = new Session();
        session.setTopic(topicService.findById(topicId));

        existsSession(session.getTopic().getId());

        setDates(session, sessionRequestDto.getSessionTimeInMinute());

        log.info("Saving topic session with id: {}", topicId);
        sessionRepository.save(session);
        return new SessionResponseDto(session);
    }

    private void setDates(Session session, Integer sessionTimeInMinute) {
        log.info("Setting the period in which the session will be open");
        session.setStartDate(LocalDateTime.now());
        Integer sessionTime = Objects.isNull(sessionTimeInMinute) ? 1 : sessionTimeInMinute;
        session.setEndDate(session.getStartDate().plusMinutes(sessionTime));
    }

    private void existsSession(Long topicId) {
        if (sessionRepository.existsByTopicId(topicId)) {
            log.error("There is an open session for the topic with id: {}", topicId);
            throw new SessionRegisteredException();
        }
    }

}
