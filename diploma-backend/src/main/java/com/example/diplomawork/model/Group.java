package com.example.diplomawork.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "groups")
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
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
