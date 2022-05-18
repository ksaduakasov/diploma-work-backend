package com.example.diplomawork.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "team_creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "team_advisor_id")
    private User advisor;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    List<TeamTopic> teamTopics;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    List<UserTeam> userTeams;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    List<Defence> teamDefences;

    private Boolean confirmed = false;

    private Integer choiceQuantity = 3;

    public Team(Long id, @NonNull String name, User creator, Boolean confirmed) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.confirmed = confirmed;
    }
}
