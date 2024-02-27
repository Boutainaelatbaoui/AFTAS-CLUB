package com.aftas.aftasbackend.model.entities;

import com.aftas.aftasbackend.model.entities.embedded.MemberCompetition;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Ranking {
    @EmbeddedId
    private MemberCompetition id;

    @ManyToOne
    @MapsId("competitionId")
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer rank;
    private Integer score;
}

