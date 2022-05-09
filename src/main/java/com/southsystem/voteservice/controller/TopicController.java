package com.southsystem.voteservice.controller;

<<<<<<< HEAD
import com.southsystem.voteservice.TopicService;
import com.southsystem.voteservice.dto.request.TopicRequestDto;
import com.southsystem.voteservice.dto.response.TopicResponseDto;
=======
import com.southsystem.voteservice.service.TopicService;
import com.southsystem.voteservice.dto.request.TopicRequestDto;
import com.southsystem.voteservice.dto.response.TopicResponseDto;
import io.swagger.annotations.ApiOperation;
>>>>>>> fbf3a974dd247b760d1a96c223ba4e1cf9e8d39f
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
=======
import javax.validation.Valid;

>>>>>>> fbf3a974dd247b760d1a96c223ba4e1cf9e8d39f
@RequestMapping("/api/v1/topic")
@RestController
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

<<<<<<< HEAD
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<TopicResponseDto> save(@RequestBody TopicRequestDto topicRequestDto) {
=======
    @ApiOperation(value = "Save a topic")
    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<TopicResponseDto> save(@Valid @RequestBody TopicRequestDto topicRequestDto) {
>>>>>>> fbf3a974dd247b760d1a96c223ba4e1cf9e8d39f
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.save(topicRequestDto)) ;
    }

}
