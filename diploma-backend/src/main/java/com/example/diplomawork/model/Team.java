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
    private String teamName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic teamTopic;

    @ManyToOne
    @JoinColumn(name = "team_creator_id")
    private User teamCreator;

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
}
