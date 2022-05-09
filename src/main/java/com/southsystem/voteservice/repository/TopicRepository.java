package com.southsystem.voteservice.repository;

import com.southsystem.voteservice.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

=======
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findBySessionEndDateBeforeAndSendResultFalse(LocalDateTime now);

>>>>>>> fbf3a974dd247b760d1a96c223ba4e1cf9e8d39f
}
