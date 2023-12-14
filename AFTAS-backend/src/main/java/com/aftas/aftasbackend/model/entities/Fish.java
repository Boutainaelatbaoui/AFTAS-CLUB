package com.aftas.aftasbackend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fishes")
public class Fish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double averageWeight;

    @OneToMany(mappedBy = "fish")
    @JsonIgnore
    private List<Hunting> huntings;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;
}
