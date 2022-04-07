package com.example.diplomawork.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String middleName;

    private String email;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "topicCreator", fetch = FetchType.LAZY)
    private List<Topic> userCreatedTopics;

    @OneToMany(mappedBy = "teamCreator", fetch = FetchType.LAZY)
    private List<Team> createdTeam;

    @OneToMany(mappedBy = "teamAdvisor", fetch = FetchType.LAZY)
    private List<Team> advisorTeams;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<UserTeam> userTeams;

    @OneToMany(mappedBy = "questioner", fetch = FetchType.LAZY)
    List<Question> userQuestions;
}

