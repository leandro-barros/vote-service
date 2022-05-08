package com.southsystem.voteservice.integration;

import com.southsystem.voteservice.dto.integration.DocumentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "email", url = "https://user-info.herokuapp.com/users")
public interface DocumentClient {

    @GetMapping("{cpf}")
    ResponseEntity<DocumentDto> findByCpf(@PathVariable String cpf);
}
