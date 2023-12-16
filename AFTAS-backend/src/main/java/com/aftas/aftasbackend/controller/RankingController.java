package com.aftas.aftasbackend.controller;

import com.aftas.aftasbackend.model.entities.Ranking;
import com.aftas.aftasbackend.service.IRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rankings")
public class RankingController {

    private final IRankingService rankingService;

    @Autowired
    public RankingController(IRankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping ("/{competitionId}")
    public ResponseEntity<List<Ranking>> rankingsForCompetition(@PathVariable Long competitionId) {
        return ResponseEntity.ok(rankingService.rankingsForCompetition(competitionId));
    }

    @GetMapping("/competition/{competitionId}")
    public List<Ranking> getRankingForCompetition(@PathVariable Long competitionId) {
        return rankingService.getRankingForCompetition(competitionId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ranking>> getAllRankings() {
        return ResponseEntity.ok(rankingService.getAllRankings());
    }
}

