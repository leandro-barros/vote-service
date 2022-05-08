package com.southsystem.voteservice.service;

import com.southsystem.voteservice.dto.request.AssociateRequestDto;
import com.southsystem.voteservice.dto.response.AssociateResponseDto;
import com.southsystem.voteservice.model.Associate;
import com.southsystem.voteservice.repository.AssociateRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociateService {

    private final AssociateRepository associateRepository;

    public AssociateService(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    public AssociateResponseDto save(AssociateRequestDto associateRequestDto) {
        Associate associateSaved = associateRepository.save(associateRequestDto.toAssociate());
        return new AssociateResponseDto(associateSaved);
    }

    public Associate findById(Long id) {
        Optional<Associate> associate = associateRepository.findById(id);
        if (!associate.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return associate.get();
    }

}
