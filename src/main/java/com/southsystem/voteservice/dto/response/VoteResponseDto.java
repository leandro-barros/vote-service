package com.southsystem.voteservice.dto.response;

import com.southsystem.voteservice.model.Vote;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoteResponseDto {

    private Long id;

    private String topicSubject;

    private String associateName;

    private String associateCPF;

    private Boolean vote;

    public VoteResponseDto(Vote vote) {
        this.id = vote.getId();
        this.topicSubject = vote.getTopic().getSubject();
        this.associateName = vote.getAssociate().getName();
        this.associateCPF = vote.getAssociate().getCpf();
        this.vote = vote.getVote();
    }

}
