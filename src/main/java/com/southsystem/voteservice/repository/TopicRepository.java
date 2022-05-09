package com.southsystem.voteservice.repository;

import com.southsystem.voteservice.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findBySessionEndDateBeforeAndSendResultFalse(LocalDateTime now);

}