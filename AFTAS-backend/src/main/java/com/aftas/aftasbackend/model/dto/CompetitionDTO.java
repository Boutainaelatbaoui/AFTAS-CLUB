package com.aftas.aftasbackend.model.dto;

import com.aftas.aftasbackend.model.entities.Hunting;
import com.aftas.aftasbackend.model.entities.Ranking;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
public class CompetitionDTO {
    private Long id;

    @Column(unique = false)
    @NotBlank(message = "The code should not be Empty")
    private String code;

   @Future(message = "The date should be in Future")
    private LocalDate date;

    @NotNull(message = "start time should not be Empty")
    private LocalTime startTime;

    @NotNull(message = "end time should not be Empty")
    private LocalTime endTime;

    @NotNull(message = "limit participants should not be Empty")
    @Min(value = 3, message = "The number of participants should not be less than 3")
    private Integer limitParticipants;

    private Integer numberOfParticipants;

    @NotBlank(message = "The location should not be Empty")
    private String location;

    @Min(value = 1, message = "The amount should not be less than 1")
    private Double amount;
}
