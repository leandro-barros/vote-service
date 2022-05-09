package com.southsystem.voteservice.service.job;

import com.southsystem.voteservice.dto.response.VoteResultDto;
import com.southsystem.voteservice.model.Topic;
import com.southsystem.voteservice.producer.VoteProducer;
import com.southsystem.voteservice.service.TopicService;
import com.southsystem.voteservice.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class VoteScheduleJob {

    private final TopicService topicService;

    private final VoteService voteService;

    private final VoteProducer voteProducer;

    public VoteScheduleJob(TopicService topicService, VoteService voteService, VoteProducer voteProducer) {
        this.topicService = topicService;
        this.voteService = voteService;
        this.voteProducer = voteProducer;
    }

    @Scheduled(cron = "15 * * * * ?", zone = "America/Sao_Paulo")
    public void sendResultVoted() {
        log.info("Running schedule");
        List<Topic> topics = topicService.findBySessionEndDateBeforeAndSendResultFalse();

        topics.forEach(i -> {
            VoteResultDto voteResultDto = voteService.resultVoted(i);
            voteProducer.sendMessageResultVote(voteResultDto);

            topicService.updateTopicAfterProduceResultVote(i);
        });
        log.info("finished schedule");
    }
}
