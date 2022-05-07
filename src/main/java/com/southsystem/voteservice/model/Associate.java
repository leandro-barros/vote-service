package com.southsystem.voteservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "associate")
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpf;

}
