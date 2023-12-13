package com.aftas.aftasbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.*;

@Getter
@Setter
public class FishDTO {
    private Long fishId;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Average weight cannot be null")
    @Positive(message = "Average weight must be a positive number")
    @Min(value = 1, message = "Average weight should be greater than 1")
    private Double averageWeight;
}
