package com.aftas.aftasbackend.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.*;

@Getter
@Setter
@Builder
public class LevelDTO {
    private Long id;

    private Integer code;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    private Integer points;
}
