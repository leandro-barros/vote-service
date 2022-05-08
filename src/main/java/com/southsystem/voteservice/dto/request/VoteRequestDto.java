package com.southsystem.voteservice.dto.request;

import com.southsystem.voteservice.model.Associate;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class VoteRequestDto {

    @NotNull
    private Associate associate;

    @NotNull
    private Boolean vote;

}
