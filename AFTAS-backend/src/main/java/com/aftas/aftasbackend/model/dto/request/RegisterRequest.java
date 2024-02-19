package com.aftas.aftasbackend.model.dto.request;

import com.aftas.aftasbackend.enums.IdentityDocumentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Family name cannot be blank")
    @Size(min = 2, max = 50, message = "Family name must be between 2 and 50 characters")
    private String familyName;

    private LocalDate accessionDate;

    @NotBlank(message = "Nationality cannot be blank")
    private String nationality;

    @NotNull(message = "Identity document type cannot be null")
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;

    @NotBlank(message = "Identity number cannot be blank")
    private String identityNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @Min(value = 6, message = "The Password must has 6 or more caracters")
    private String password;
    private Long role_id;
}
