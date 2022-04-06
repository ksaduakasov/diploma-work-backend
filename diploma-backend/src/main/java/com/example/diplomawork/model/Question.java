package com.example.diplomawork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "questioner_id", nullable = false)
    private User questioner;

    @ManyToOne
    @JoinColumn(name = "defence_id", nullable = false)
    private Defence defence;

    private Integer grade;
}
