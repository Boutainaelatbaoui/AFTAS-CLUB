package com.aftas.aftasbackend.model.dto;

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
    private String identityNumber;
}

