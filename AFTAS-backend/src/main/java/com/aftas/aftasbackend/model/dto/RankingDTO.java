package com.aftas.aftasbackend.model.dto;

import com.aftas.aftasbackend.model.entities.Competition;
import com.aftas.aftasbackend.model.entities.Member;
import lombok.*;

@Getter
@Setter
public class RankingDTO {
    private Integer rank;
    private Integer score;
    private Competition competition;
    private Member member;
}

