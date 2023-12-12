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
import java.util.stream.Collectors;

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

    @Override
    public List<CompetitionDTO> getAllCompetitions() {
        List<Competition> competitions = competitionRepository.findAll();
        return competitions.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public Competition getCompetitionById(Long competitionId) {
        return competitionRepository.findById(competitionId)
                .orElseThrow(() -> new ValidationException("Competition not found with id: " + competitionId));
    }

    @Override
    public Competition updateCompetition(Long competitionId, CompetitionDTO competitionDTO) {
        Competition existingCompetition = getCompetitionById(competitionId);

        if(!isDateNotInCompetition(competitionDTO.getDate())){
            throw new ValidationException("A competition already exists on the specified date.");
        }
        if (!isCodeValid(competitionDTO.getCode(), competitionDTO.getLocation(), competitionDTO.getDate())){
            throw new ValidationException("Invalid code format! should be like this ex: Imsouane: pattern: ims-22-12-23.");
        }

        existingCompetition.setCode(competitionDTO.getCode());
        existingCompetition.setDate(competitionDTO.getDate());
        existingCompetition.setStartTime(competitionDTO.getStartTime());
        existingCompetition.setEndTime(competitionDTO.getEndTime());
        existingCompetition.setNumberOfParticipants(competitionDTO.getNumberOfParticipants());
        existingCompetition.setLocation(competitionDTO.getLocation());
        existingCompetition.setAmount(competitionDTO.getAmount());

        return competitionRepository.save(existingCompetition);
    }

    @Override
    public void deleteCompetition(Long competitionId) {
        Competition existingCompetition = getCompetitionById(competitionId);
        competitionRepository.delete(existingCompetition);
    }

    private Competition mapDTOToEntity(CompetitionDTO competitionDTO) {
        return Competition.builder()
                .id(competitionDTO.getId())
                .code(competitionDTO.getCode())
                .date(competitionDTO.getDate())
                .startTime(competitionDTO.getStartTime())
                .endTime(competitionDTO.getEndTime())
                .numberOfParticipants(competitionDTO.getNumberOfParticipants())
                .location(competitionDTO.getLocation())
                .amount(competitionDTO.getAmount())
                .build();
    }

    private CompetitionDTO mapEntityToDTO(Competition competition) {
        return CompetitionDTO.builder()
                .id(competition.getId())
                .code(competition.getCode())
                .date(competition.getDate())
                .startTime(competition.getStartTime())
                .endTime(competition.getEndTime())
                .numberOfParticipants(competition.getNumberOfParticipants())
                .location(competition.getLocation())
                .amount(competition.getAmount())
                .build();
    }
}
