package com.example.diplomawork.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String topicName;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "topic_creator_id", nullable = false)
    private User topicCreator;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "initial_id", nullable = false)
    private Initial initial;

    private Boolean selected = false;

    @OneToOne(mappedBy = "teamTopic")
    private Team team;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private List<TeamTopic> topicTeam;
}
