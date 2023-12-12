package com.aftas.aftasbackend.model.dto;

import lombok.*;

@Getter
@Setter
public class HuntingDTO {
    private Long id;
    private Integer numberOfFish;
    private Long fishId;
    private Long competitionId;
    private Long levelId;
    private Long memberId;
}

