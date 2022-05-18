package com.example.diplomawork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "initial_id", nullable = false)
    private Initial initial;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<User> groupUsers;

    public Group(Long id, String name, Initial initial) {
        this.id = id;
        this.name = name;
        this.initial = initial;
    }
}
