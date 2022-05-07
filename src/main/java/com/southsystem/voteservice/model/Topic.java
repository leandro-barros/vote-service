package com.southsystem.voteservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "topic")
    private Session session;

    private String subject;

    private String description;
}
