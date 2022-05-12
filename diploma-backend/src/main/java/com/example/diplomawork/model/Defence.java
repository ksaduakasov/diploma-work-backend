package com.example.diplomawork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "defences")
public class Defence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate defenceDate;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;

    @OneToMany(mappedBy = "defence", fetch = FetchType.LAZY)
    List<Question> defenceQuestions;

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    List<Document> defenceDocuments;

    private Integer grade;
}
