package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.model.dto.CompetitionDTO;
import com.aftas.aftasbackend.model.entities.Competition;
import com.aftas.aftasbackend.repository.CompetitionRepository;
import com.aftas.aftasbackend.service.ICompetitionService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompetitionServiceImpl implements ICompetitionService {
    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition createCompetition(CompetitionDTO competitionDTO) {
        if(!isDateNotInCompetition(competitionDTO.getDate())){
            throw new ValidationException("A competition already exists on the specified date.");
        }
        Competition competition =mapDTOToEntity(competitionDTO);
        return competitionRepository.save(competition);
    }

    private Competition mapDTOToEntity(CompetitionDTO competitionDTO) {
        return Competition.builder()
                .code(competitionDTO.getCode())
                .amount(competitionDTO.getAmount())
                .date(competitionDTO.getDate())
                .endTime(competitionDTO.getEndTime())
                .startTime(competitionDTO.getStartTime())
                .location(competitionDTO.getLocation())
                .numberOfParticipants(competitionDTO.getNumberOfParticipants())
                .build();
    }

    private boolean isDateNotInCompetition(LocalDate date) {
        List<Competition> existingCompetitions = competitionRepository.findCompetitionByDate(date);

        return existingCompetitions.isEmpty();
    }
}
