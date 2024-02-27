package com.aftas.aftasbackend.controller;

import com.aftas.aftasbackend.model.entities.Competition;
import com.aftas.aftasbackend.model.entities.Ranking;
import com.aftas.aftasbackend.service.IRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rankings")
@CrossOrigin(origins = "http://localhost:4200")
public class RankingController {

    private final IRankingService rankingService;

    @Autowired
    public RankingController(IRankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping ("/{competitionId}")
    @PreAuthorize("hasAuthority('CAN_MANAGE_COMPETITIONS')")
    public ResponseEntity<List<Ranking>> rankingsForCompetition(@PathVariable Long competitionId) {
        return ResponseEntity.ok(rankingService.rankingsForCompetition(competitionId));
    }

    @GetMapping("/competition/{competitionId}")
    @PreAuthorize("hasAuthority('CAN_READ_PODIUM_INFO')")
    public List<Ranking> getRankingForCompetition(@PathVariable Long competitionId) {
        return rankingService.getRankingForCompetition(competitionId);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('CAN_MANAGE_COMPETITIONS')")
    public ResponseEntity<List<Ranking>> getAllRankings() {
        return ResponseEntity.ok(rankingService.getAllRankings());
    }

    @GetMapping("/member/{memberId}")
    @PreAuthorize("hasAuthority('CAN_READ_PARTICIPATIONS')")
    public ResponseEntity<List<Competition>> getMemberCompetitions(@PathVariable Long memberId) {
        return ResponseEntity.ok(rankingService.getMemberCompetitions(memberId));
    }
}

