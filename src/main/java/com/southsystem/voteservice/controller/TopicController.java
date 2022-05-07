package com.southsystem.voteservice.controller;

import com.southsystem.voteservice.service.TopicService;
import com.southsystem.voteservice.dto.request.TopicRequestDto;
import com.southsystem.voteservice.dto.response.TopicResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/topic")
@RestController
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<TopicResponseDto> save(@RequestBody TopicRequestDto topicRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.save(topicRequestDto)) ;
    }

}
