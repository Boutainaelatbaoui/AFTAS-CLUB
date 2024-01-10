package com.aftas.aftasbackend.model.dto;

import com.aftas.aftasbackend.model.entities.Level;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.*;

@Getter
@Setter
@Builder
public class FishDTO {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Average weight cannot be null")
    @DecimalMin(value = "2", message = "Average weight should be greater than 1")
    private Double averageWeight;
    @NotNull(message = "levelID cannot be null")
    private Long level_id;
}
