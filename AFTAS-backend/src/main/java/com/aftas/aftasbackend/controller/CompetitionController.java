package com.aftas.aftasbackend.controller;

import com.aftas.aftasbackend.model.dto.CompetitionDTO;
import com.aftas.aftasbackend.model.entities.Competition;
import com.aftas.aftasbackend.service.ICompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final ICompetitionService competitionService;

    @Autowired
    public CompetitionController(ICompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @PostMapping("/create")
    public ResponseEntity<Competition> createCompetition(@RequestBody @Valid CompetitionDTO competitionDTO) {
        Competition createdCompetition = competitionService.createCompetition(competitionDTO);
        return new ResponseEntity<>(createdCompetition, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompetitionDTO>> getAllCompetitions(){
        List<CompetitionDTO> competitions = competitionService.getAllCompetitions();
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

}
