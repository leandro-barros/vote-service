package com.southsystem.voteservice.dto.request;

import com.southsystem.voteservice.model.Associate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssociateRequestDto {

    private String name;

    private String cpf;

    public Associate toAssociate() {
        return new Associate(null, name, cpf);
    }

}
