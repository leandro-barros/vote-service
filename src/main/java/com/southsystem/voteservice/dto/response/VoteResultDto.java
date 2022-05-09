package com.southsystem.voteservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
public class VoteResultDto implements Serializable {

    private String topic;

    private Long voteInFavor;

    private Long voteAgainst;

    private String result;

}
