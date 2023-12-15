package com.aftas.aftasbackend.service;

import org.springframework.stereotype.Service;

@Service
public interface IRankingService {
    void rankingsForCompetition(Long competitionId);
}

