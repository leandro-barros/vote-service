package com.southsystem.voteservice.dto.response;

import com.southsystem.voteservice.model.Associate;
import com.southsystem.voteservice.model.Session;
import com.southsystem.voteservice.model.Topic;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AssociateResponseDto {

    private Long id;

    private String name;

    private String cpf;

    public AssociateResponseDto(Associate associate) {
        this.name = associate.getName();
        this.cpf = associate.getCpf();
        this.id = associate.getId();
    }

}
