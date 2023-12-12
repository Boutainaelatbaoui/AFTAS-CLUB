package com.aftas.aftasbackend.controller;

import com.aftas.aftasbackend.model.dto.CompetitionDTO;
import com.aftas.aftasbackend.model.entities.Competition;
import com.aftas.aftasbackend.service.ICompetitionService;
import org.modelmapper.ModelMapper;
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

    @PostMapping("")
    public ResponseEntity<Competition> createCompetition(@RequestBody @Valid CompetitionDTO competitionDTO) {
        Competition createdCompetition = competitionService.createCompetition(competitionDTO);
        return new ResponseEntity<>(createdCompetition, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompetitionDTO>> getAllCompetitions(){
        List<CompetitionDTO> competitions = competitionService.getAllCompetitions();
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @GetMapping("/{competitionId}")
    public ResponseEntity<Competition> getCompetitionById(@PathVariable Long competitionId) {
        Competition competition = competitionService.getCompetitionById(competitionId);
        return new ResponseEntity<>(competition, HttpStatus.OK);
    }

    @PutMapping("/{competitionId}")
    public ResponseEntity<Competition> updateCompetition(
            @PathVariable Long competitionId,
            @RequestBody @Valid CompetitionDTO competitionDTO) {
        Competition updatedCompetition = competitionService.updateCompetition(competitionId, competitionDTO);
        return new ResponseEntity<>(updatedCompetition, HttpStatus.OK);
    }

    @DeleteMapping("/{competitionId}")
    public ResponseEntity<String> deleteCompetition(@PathVariable Long competitionId) {
        competitionService.deleteCompetition(competitionId);
        return new ResponseEntity<>("The Competition has been deleted.",HttpStatus.NO_CONTENT);
    }

}
