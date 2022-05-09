package com.southsystem.voteservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
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
<<<<<<< HEAD
=======

    @Column(name = "send_result")
    private Boolean sendResult;
>>>>>>> fbf3a974dd247b760d1a96c223ba4e1cf9e8d39f
}
