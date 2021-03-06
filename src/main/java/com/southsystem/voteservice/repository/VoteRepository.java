package com.southsystem.voteservice.repository;

import com.southsystem.voteservice.model.Topic;
import com.southsystem.voteservice.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByAssociateIdAndTopicId(Long associateId, Long topicId);

    Long countByTopicAndVoteTrue(Topic topic);

    Long countByTopicAndVoteFalse(Topic topic);

}
