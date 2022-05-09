package com.southsystem.voteservice.model;

<<<<<<< HEAD
import lombok.Data;

import javax.persistence.*;

=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
>>>>>>> fbf3a974dd247b760d1a96c223ba4e1cf9e8d39f
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
