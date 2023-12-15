package com.aftas.aftasbackend.controller;

import com.aftas.aftasbackend.service.IRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rankings")
public class RankingController {

    private final IRankingService rankingService;

    @Autowired
    public RankingController(IRankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping ("/{competitionId}")
    public ResponseEntity<String> rankingsForCompetition(@PathVariable Long competitionId) {
        rankingService.rankingsForCompetition(competitionId);
        return ResponseEntity.ok("Rankings updated successfully.");
    }
}

