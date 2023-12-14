package com.aftas.aftasbackend.model.dto;

import com.aftas.aftasbackend.model.entities.Competition;
import com.aftas.aftasbackend.model.entities.Fish;
import com.aftas.aftasbackend.model.entities.Member;
import lombok.*;

import jakarta.validation.constraints.*;

@Getter
@Setter
@Builder
public class HuntingDTO {
    private Long id;

    private Integer numberOfFish;

    @NotNull(message = "Fish ID cannot be null")
    @Positive(message = "Fish ID should be a positive value")
    private Long fishId;

    @NotNull(message = "Competition ID cannot be null")
    @Positive(message = "Competition ID should be a positive value")
    private Long competitionId;

    @NotNull(message = "Member ID cannot be null")
    @Positive(message = "Member ID should be a positive value")
    private Long memberId;
    private Fish fish;
    private Competition competition;
    private Member member;
}


