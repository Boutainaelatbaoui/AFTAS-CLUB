package com.aftas.aftasbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.*;

@Getter
@Setter
public class LevelDTO {
    private Long id;

    @NotNull(message = "Code cannot be null")
    private Integer code;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Points cannot be null")
    @Positive(message = "Points must be a positive number")
    @Min(value = 10, message = "Points must be greater or equal to 10")
    private Integer points;
}
