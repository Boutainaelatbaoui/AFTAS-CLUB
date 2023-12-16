package com.aftas.aftasbackend.service;

import com.aftas.aftasbackend.model.entities.Ranking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRankingService {
    List<Ranking> rankingsForCompetition(Long competitionId);
    List<Ranking> getRankingForCompetition(Long competitionId);
    List<Ranking> getAllRankings();
}

