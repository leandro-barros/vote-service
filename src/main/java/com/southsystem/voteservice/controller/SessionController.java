package com.southsystem.voteservice.controller;

import com.southsystem.voteservice.dto.request.SessionRequestDto;
import com.southsystem.voteservice.dto.response.SessionResponseDto;
import com.southsystem.voteservice.exceptionhandler.VoteServiceExceptionHandler.Erro;
import com.southsystem.voteservice.service.SessionService;
import com.southsystem.voteservice.service.exception.SessionRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/v1/session")
@RestController
public class SessionController {

    @Autowired
    private MessageSource messageSource;
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/open")
    public ResponseEntity<SessionResponseDto> openSession(@Valid @RequestBody SessionRequestDto sessionRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.openSession(sessionRequestDto)) ;
    }

    @ExceptionHandler({ SessionRegisteredException.class })
    public ResponseEntity<Object> handleSessionRegistered(SessionRegisteredException ex) {
        String messageUser = messageSource.getMessage("session.registered", null,
                                    LocaleContextHolder.getLocale());
        String messageDevelop = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelop));
        return new ResponseEntity<>(erros, HttpStatus.CONFLICT);
    }

}