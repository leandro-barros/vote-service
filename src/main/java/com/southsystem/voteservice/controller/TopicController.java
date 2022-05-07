package com.southsystem.voteservice.controller;

import com.southsystem.voteservice.service.TopicService;
import com.southsystem.voteservice.dto.request.TopicRequestDto;
import com.southsystem.voteservice.dto.response.TopicResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/topic")
@RestController
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<TopicResponseDto> save(@Valid @RequestBody TopicRequestDto topicRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.save(topicRequestDto)) ;
    }

}
