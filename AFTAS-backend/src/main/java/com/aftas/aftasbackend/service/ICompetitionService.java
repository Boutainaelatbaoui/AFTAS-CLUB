package com.aftas.aftasbackend.service;

import com.aftas.aftasbackend.model.dto.CompetitionDTO;
import com.aftas.aftasbackend.model.entities.Competition;
import org.springframework.stereotype.Service;

@Service
public interface ICompetitionService {
    public Competition createCompetition(CompetitionDTO competitionDTO);
}

