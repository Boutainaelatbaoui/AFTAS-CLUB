package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.model.dto.CompetitionDTO;
import com.aftas.aftasbackend.model.entities.Competition;
import com.aftas.aftasbackend.repository.CompetitionRepository;
import com.aftas.aftasbackend.service.ICompetitionService;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        if (!isCodeValid(competitionDTO.getCode(), competitionDTO.getLocation(), competitionDTO.getDate())){
            throw new ValidationException("Invalid code format! should be like this ex: Imsouane: pattern: ims-22-12-23.");
        }
        Competition competition =mapDTOToEntity(competitionDTO);
        return competitionRepository.save(competition);
    }

    private Competition mapDTOToEntity(CompetitionDTO competitionDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(competitionDTO, Competition.class);
    }

    private boolean isDateNotInCompetition(LocalDate date) {
        List<Competition> existingCompetitions = competitionRepository.findCompetitionByDate(date);

        return existingCompetitions.isEmpty();
    }

    private String generateCode(String location, LocalDate date) {
        String locationPart = location.substring(0, Math.min(location.length(), 3)).toLowerCase();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
        return locationPart + "-" + formattedDate;
    }

    private boolean isCodeValid(String code, String location, LocalDate date) {
        String generatedCode = generateCode(location, date);

        return generatedCode.equalsIgnoreCase(code);
    }



}
