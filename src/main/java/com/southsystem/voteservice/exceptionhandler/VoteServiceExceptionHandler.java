package com.southsystem.voteservice.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
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

        String menssageUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String menssageDevelop = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(menssageUser, menssageDevelop));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }


    private List<Erro> createListErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String menssageUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String menssageDevelop = fieldError.toString();
            erros.add(new Erro(menssageUser, menssageDevelop));
        }

        return erros;
    }

    public static class Erro {

        private String menssageUser;
        private String menssageDevelop;

        public Erro(String menssageUser, String menssageDevelop) {
            this.menssageUser = menssageUser;
            this.menssageDevelop = menssageDevelop;
        }

        public String getMenssageUser() {
            return menssageUser;
        }

        public String getMenssageDevelop() {
            return menssageDevelop;
        }
    }
}
