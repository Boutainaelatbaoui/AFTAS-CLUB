package com.aftas.aftasbackend.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rank;
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}

