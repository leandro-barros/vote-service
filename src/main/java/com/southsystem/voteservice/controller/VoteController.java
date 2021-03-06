package com.southsystem.voteservice.controller;

import com.southsystem.voteservice.dto.request.VoteRequestDto;
import com.southsystem.voteservice.dto.response.VoteResponseDto;
import com.southsystem.voteservice.dto.response.VoteResultDto;
import com.southsystem.voteservice.exceptionhandler.VoteServiceExceptionHandler.Erro;
import com.southsystem.voteservice.service.VoteService;
import com.southsystem.voteservice.service.exception.RegisteredVotedException;
import com.southsystem.voteservice.service.exception.SessionNotOpenException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/v1/vote")
@RestController
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @Autowired
    private MessageSource messageSource;

    @ApiOperation(value = "Register a associate vote on a topic")
    @PostMapping(value = "/topic/{topicId}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<VoteResponseDto> saveVote(@PathVariable Long topicId, @Valid @RequestBody VoteRequestDto voteRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(voteService.saveVote(topicId, voteRequestDto)) ;
    }

    @ApiOperation(value = "Return the poll result of a topic")
    @GetMapping(value = "/topic/{topicId}/result", produces = "application/json;charset=UTF-8")
    public ResponseEntity<VoteResultDto> resultVoting(@PathVariable Long topicId) {
        return ResponseEntity.status(HttpStatus.OK).body(voteService.result(topicId)) ;
    }

    @ExceptionHandler({ SessionNotOpenException.class })
    public ResponseEntity<Object> handleSessionNotOpen(SessionNotOpenException ex) {
        String messageUser = messageSource.getMessage("session.notopen", null,
                                LocaleContextHolder.getLocale());
        String messageDevelop = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelop));
        return new ResponseEntity<>(erros, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ RegisteredVotedException.class })
    public ResponseEntity<Object> handleRegisteredVoted(RegisteredVotedException ex) {
        String messageUser = messageSource.getMessage("registered.voted", null,
                                     LocaleContextHolder.getLocale());
        String messageDevelop = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelop));
        return new ResponseEntity<>(erros, HttpStatus.CONFLICT);
    }

}
