package com.southsystem.voteservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class VoteResultDto {


    private String topic;

    private Long voteInFavor;

    private Long voteAgainst;

    private String result;

}
