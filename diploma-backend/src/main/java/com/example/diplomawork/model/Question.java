package com.example.diplomawork.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "questions")
@Builder
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
