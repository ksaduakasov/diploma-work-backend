package com.example.diplomawork.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
