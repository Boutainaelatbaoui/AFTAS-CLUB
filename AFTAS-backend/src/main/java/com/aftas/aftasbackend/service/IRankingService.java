package com.aftas.aftasbackend.service;

import com.aftas.aftasbackend.model.entities.Ranking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRankingService {
    void rankingsForCompetition(Long competitionId);
    List<Ranking> getRankingForCompetition(Long competitionId);
}

