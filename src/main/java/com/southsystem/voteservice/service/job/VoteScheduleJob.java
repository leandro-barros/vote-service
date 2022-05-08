package com.southsystem.voteservice.service.job;

import com.southsystem.voteservice.dto.response.VoteResultDto;
import com.southsystem.voteservice.model.Topic;
import com.southsystem.voteservice.repository.TopicRepository;
import com.southsystem.voteservice.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class VoteScheduleJob {

    private final TopicRepository topicRepository;

    private final VoteService voteService;

    public VoteScheduleJob(TopicRepository topicRepository, VoteService voteService) {
        this.topicRepository = topicRepository;
        this.voteService = voteService;
    }

    @Scheduled(cron = "15 0 0 * * ?", zone = "America/Sao_Paulo")
    public void sendResultVoted() {
       List<Topic> topics = topicRepository.findBySessionEndDateBefore(LocalDateTime.now());

       topics.forEach(i -> {
           VoteResultDto voteResultDto = voteService.resultVoted(i);
            log.info("Result voted {}", voteResultDto);
       });
    }
}
