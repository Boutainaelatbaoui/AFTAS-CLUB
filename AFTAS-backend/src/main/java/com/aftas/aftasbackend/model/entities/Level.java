package com.aftas.aftasbackend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer code;

    private String description;
    private Integer points;

    @OneToMany(mappedBy = "level")
    @JsonIgnore
    private List<Fish> fishes;
}
