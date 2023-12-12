package com.aftas.aftasbackend.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fishes")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private Double averageWeight;

    @OneToMany(mappedBy = "fish")
    private List<Hunting> huntings;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;
}
