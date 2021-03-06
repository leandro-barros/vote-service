package com.southsystem.voteservice.exceptionhandler;

import com.southsystem.voteservice.service.exception.InvalidCpfException;
import com.southsystem.voteservice.service.exception.UnableToVoteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class VoteServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String messageUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String messageDevelop = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelop));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Erro> erros = createListErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                        WebRequest request) {
        String messageUser = messageSource.getMessage("recurso.operacao.nao-permitida", null,
                LocaleContextHolder.getLocale());
        String messageDevelop = ex.toString();

        List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelop));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
                                                                        WebRequest request) {
        String messageUser = messageSource.getMessage("recurso.nao-encontrado", null,
                                        LocaleContextHolder.getLocale());
        String messageDevelop = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelop));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ InvalidCpfException.class })
    public ResponseEntity<Object> handleInvalidCpfException(InvalidCpfException ex) {
        String messageUser = messageSource.getMessage("cpf.invalid", null,
                                                        LocaleContextHolder.getLocale());
        String messageDevelop = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelop));
        return new ResponseEntity<>(erros, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ UnableToVoteException.class })
    public ResponseEntity<Object> handleUnableToVoteException(UnableToVoteException ex) {
        String messageUser = messageSource.getMessage("unabled.to.vote", null,
                                                        LocaleContextHolder.getLocale());
        String messageDevelop = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelop));
        return new ResponseEntity<>(erros, HttpStatus.CONFLICT);
    }

    private List<Erro> createListErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String messageUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String messageDevelop = fieldError.toString();
            erros.add(new Erro(messageUser, messageDevelop));
        }

        return erros;
    }

    public static class Erro {

        private String messageUser;
        private String messageDevelop;

        public Erro(String menssageUser, String menssageDevelop) {
            this.messageUser = menssageUser;
            this.messageDevelop = menssageDevelop;
        }

        public String getMessageUser() {
            return messageUser;
        }

        public String getMessageDevelop() {
            return messageDevelop;
        }
    }
}
