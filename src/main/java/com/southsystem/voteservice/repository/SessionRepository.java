package com.southsystem.voteservice.repository;

import com.southsystem.voteservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    boolean existsByTopicId(Long topicId);

}
