package com.southsystem.voteservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "associate_id")
    private Topic associate;

    private Boolean vote;

}
