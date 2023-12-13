package com.aftas.aftasbackend.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    private Integer numberOfParticipants;
    private String location;
    private Double amount;
    private Integer limitParticipants;

    @OneToMany(mappedBy = "competition")
    private List<Hunting> huntings;

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;
}
