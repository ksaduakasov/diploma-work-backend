package com.example.diplomawork.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Builder
public class Reviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String careerGrade;

    private String workPlace;

    private String profession;

    @OneToMany(mappedBy = "reviewer", fetch = FetchType.LAZY)
    private List<Team> teams;
}
