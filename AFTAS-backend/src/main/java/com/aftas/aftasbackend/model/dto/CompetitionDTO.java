package com.aftas.aftasbackend.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CompetitionDTO {
    private Long id;

    @NotBlank(message = "The code should not be Empty")
    private String code;

    @Future(message = "The date should be in Future")
    private LocalDate date;

    @NotNull(message = "start time should not be Empty")
    private LocalTime startTime;

    @NotNull(message = "end time should not be Empty")
    private LocalTime endTime;

    @Min(value = 1, message = "The number of participants should not be less than 1")
    private Integer numberOfParticipants;

    @NotBlank(message = "The location should not be Empty")
    private String location;

    @Min(value = 1, message = "The amount should not be less than 1")
    private Double amount;
}
