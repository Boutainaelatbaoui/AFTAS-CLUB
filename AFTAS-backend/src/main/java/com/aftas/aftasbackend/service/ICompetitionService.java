package com.aftas.aftasbackend.service;

import com.aftas.aftasbackend.model.dto.CompetitionDTO;
import com.aftas.aftasbackend.model.entities.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICompetitionService {
    public Competition createCompetition(CompetitionDTO competitionDTO);
    Page<CompetitionDTO> getAllCompetitions(Pageable pageable);
    public CompetitionDTO getCompetitionById(Long competitionId);
    public Competition updateCompetition(Long competitionId, CompetitionDTO competitionDTO);
    public void deleteCompetition(Long competitionId);
    public Competition addMemberToCompetition(Long competitionId, Long memberId);
}

