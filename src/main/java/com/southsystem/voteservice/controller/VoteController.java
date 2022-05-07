package com.southsystem.voteservice.controller;

import com.southsystem.voteservice.dto.request.TopicRequestDto;
import com.southsystem.voteservice.dto.request.VoteRequestDto;
import com.southsystem.voteservice.dto.response.TopicResponseDto;
import com.southsystem.voteservice.dto.response.VoteResponseDto;
import com.southsystem.voteservice.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/vote")
@RestController
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/topic/{topicId}")
    public ResponseEntity<VoteResponseDto> save(@PathVariable Long topicId, @Valid @RequestBody VoteRequestDto voteRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(voteService.saveVote(topicId, voteRequestDto)) ;
    }


}
