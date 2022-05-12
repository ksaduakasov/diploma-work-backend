package com.example.diplomawork.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private byte[] bytes;

    private String docType;

    private String docUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "defence_id", nullable = false)
    private Defence defence;
}
