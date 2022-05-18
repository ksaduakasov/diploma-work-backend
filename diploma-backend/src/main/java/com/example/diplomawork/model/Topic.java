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
    private String name;

    @ManyToOne
    @JoinColumn(name = "topic_creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "initial_id", nullable = false)
    private Initial initial;

    private Boolean selected;

    @OneToOne(mappedBy = "topic")
    private Team team;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private List<TeamTopic> topicTeam;

    public Topic(Long id, @NonNull String name, User creator, Initial initial, Boolean selected) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.initial = initial;
        this.selected = selected;
    }
}
