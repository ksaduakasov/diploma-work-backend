package com.example.diplomawork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "initials")
public class Initial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String initial;

    @OneToMany(mappedBy = "groupInitials", fetch = FetchType.LAZY)
    private List<Group> groups;

    @OneToMany(mappedBy = "initial", fetch = FetchType.LAZY)
    private List<Topic> initialTopics;
}
