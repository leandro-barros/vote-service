package com.southsystem.voteservice.controller;

import com.southsystem.voteservice.dto.request.VoteRequestDto;
import com.southsystem.voteservice.dto.response.VoteResponseDto;
import com.southsystem.voteservice.exceptionhandler.VoteServiceExceptionHandler.Erro;
import com.southsystem.voteservice.service.VoteService;
import com.southsystem.voteservice.service.exception.SessionNotOpenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/topic/{topicId}")
    public ResponseEntity<VoteResponseDto> save(@PathVariable Long topicId, @Valid @RequestBody VoteRequestDto voteRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(voteService.saveVote(topicId, voteRequestDto)) ;
    }

    @ExceptionHandler({ SessionNotOpenException.class })
    public ResponseEntity<Object> handleProdutoJaCadastrado(SessionNotOpenException ex) {
        String messageUser = messageSource.getMessage("session.notopen", null,
                                LocaleContextHolder.getLocale());
        String messageDevelop = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelop));
        return new ResponseEntity<>(erros, HttpStatus.CONFLICT);
    }

}
