package com.aftas.aftasbackend.model.dto;

import com.aftas.aftasbackend.enums.IdentityDocumentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import java.util.Date;

@Getter
@Setter
public class MemberDTO {
    private Long id;
    private Integer num;
    private String name;
    private String familyName;
    private Date accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;
    private String identityNumber;
}

