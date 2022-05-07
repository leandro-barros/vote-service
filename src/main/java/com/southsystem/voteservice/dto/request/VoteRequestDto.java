package com.southsystem.voteservice.dto.request;

import com.southsystem.voteservice.model.Associate;
import com.southsystem.voteservice.model.Topic;
import com.southsystem.voteservice.model.Vote;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoteRequestDto {

    private Topic topic;

    private Associate associate;

    private Boolean vote;

    public Vote toVote() {
        return new Vote(null, topic, associate, vote);
    }

}
