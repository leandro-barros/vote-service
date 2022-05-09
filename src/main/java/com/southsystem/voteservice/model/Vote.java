package com.southsystem.voteservice.model;

<<<<<<< HEAD
import lombok.Data;

import javax.persistence.*;

=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
>>>>>>> fbf3a974dd247b760d1a96c223ba4e1cf9e8d39f
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
<<<<<<< HEAD
    private Topic associate;
=======
    private Associate associate;
>>>>>>> fbf3a974dd247b760d1a96c223ba4e1cf9e8d39f

    private Boolean vote;

}
