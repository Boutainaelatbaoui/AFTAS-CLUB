package com.aftas.aftasbackend.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class LevelDTO {
    private Long id;

    private Integer code;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    private Integer points;
}
