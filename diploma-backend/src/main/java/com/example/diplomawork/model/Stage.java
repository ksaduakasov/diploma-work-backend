package com.example.diplomawork.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "stages")
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stage;

    @OneToMany(mappedBy = "stage", fetch = FetchType.LAZY)
    List<Defence> stageDefences;
}
