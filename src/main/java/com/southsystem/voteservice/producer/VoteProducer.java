package com.southsystem.voteservice.producer;

import com.southsystem.voteservice.dto.response.VoteResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Slf4j
@Component
public class VoteProducer {

    @Autowired
    private KafkaTemplate<String, Serializable> jsonObjectkafkaTemplate;

    public void sendMessageResultVote(VoteResultDto voteResultDto) {
        jsonObjectkafkaTemplate.send("topic-vote", voteResultDto).addCallback(
                success -> log.info("Sending message with vote result " + success.getProducerRecord().value()),
                failure -> log.error("Sending message with vote result failure " + failure.getMessage())
        );
    }
}
