package com.southsystem.voteservice.dto.request;

import com.southsystem.voteservice.model.Associate;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class AssociateRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    public Associate toAssociate() {
        return new Associate(null, name, cpf);
    }

}
