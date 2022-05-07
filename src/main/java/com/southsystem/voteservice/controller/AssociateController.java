package com.southsystem.voteservice.controller;

import com.southsystem.voteservice.dto.request.AssociateRequestDto;
import com.southsystem.voteservice.dto.response.AssociateResponseDto;
import com.southsystem.voteservice.service.AssociateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/v1/associate")
@RestController
public class AssociateController {

    private final AssociateService associateService;

    public AssociateController(AssociateService associateService) {
        this.associateService = associateService;
    }

    @PostMapping
    public ResponseEntity<AssociateResponseDto> save(@Valid @RequestBody AssociateRequestDto associateRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(associateService.save(associateRequestDto)) ;
    }

}
