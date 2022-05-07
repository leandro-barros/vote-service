package com.southsystem.voteservice.dto.request;

import com.southsystem.voteservice.model.Associate;
import com.southsystem.voteservice.model.Topic;
import com.southsystem.voteservice.model.Vote;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class VoteRequestDto {

    private Topic topic;

    @NotNull
    private Associate associate;

    @NotNull
    private Boolean vote;

    public Vote toVote() {
        return new Vote(null, topic, associate, vote);
    }

}
