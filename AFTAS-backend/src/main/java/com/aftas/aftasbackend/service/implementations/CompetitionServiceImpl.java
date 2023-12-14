package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.model.dto.CompetitionDTO;
import com.aftas.aftasbackend.model.entities.Competition;
import com.aftas.aftasbackend.model.entities.Member;
import com.aftas.aftasbackend.model.entities.Ranking;
import com.aftas.aftasbackend.model.entities.embedded.MemberCompetition;
import com.aftas.aftasbackend.repository.CompetitionRepository;
import com.aftas.aftasbackend.repository.MemberRepository;
import com.aftas.aftasbackend.repository.RankingRepository;
import com.aftas.aftasbackend.service.ICompetitionService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionServiceImpl implements ICompetitionService {
    private final CompetitionRepository competitionRepository;
    private final MemberRepository memberRepository;
    private final RankingRepository rankingRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, MemberRepository memberRepository, RankingRepository rankingRepository) {
        this.competitionRepository = competitionRepository;
        this.memberRepository = memberRepository;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public Competition createCompetition(CompetitionDTO competitionDTO) {
        if(!isDateNotInCompetition(competitionDTO.getDate())){
            throw new ValidationException("A competition already exists on the specified date.");
        }
        if (!isCodeValid(competitionDTO.getCode(), competitionDTO.getLocation(), competitionDTO.getDate())){
            throw new ValidationException("Invalid code format! should be like this ex: Imsouane: pattern: ims-22-12-23.");
        }
        if (competitionRepository.existsByCode(competitionDTO.getCode())){
            throw new ValidationException("The code is already exists.");
        }
        Competition competition =mapDTOToEntity(competitionDTO);
        competition.setNumberOfParticipants(0);
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
    public Competition addMemberToCompetition(Long competitionId, Long memberId) {
        Competition competition = mapDTOToEntity(getCompetitionById(competitionId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ValidationException("Member not found. Register this member in the club first."));

        if (rankingRepository.existsByCompetitionAndMember(competition, member)) {
            throw new ValidationException("Member is already added to the competition.");
        }

        if (competition.getNumberOfParticipants() >= competition.getLimitParticipants()) {
            throw new ValidationException("The competition has reached its participant limit.");
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime competitionStartDateTime = LocalDateTime.of(competition.getDate(), competition.getStartTime());

        if (competitionStartDateTime.isAfter(currentDateTime.plusHours(24))) {
            competition.setNumberOfParticipants(competition.getNumberOfParticipants() + 1);

            MemberCompetition memberCompetition = new MemberCompetition();
            memberCompetition.setCompetitionId(competition.getId());
            memberCompetition.setMemberId(member.getId());

            Ranking ranking = new Ranking();
            ranking.setId(memberCompetition);
            ranking.setCompetition(competition);
            ranking.setMember(member);

            rankingRepository.save(ranking);

            return competitionRepository.save(competition);
        } else {
            throw new ValidationException("You can be added to this competition just before 24 hours!!");
        }
    }




    @Override
    public List<CompetitionDTO> getAllCompetitions() {
        List<Competition> competitions = competitionRepository.findAll();
        return competitions.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public CompetitionDTO getCompetitionById(Long competitionId) {
        Optional<Competition> competitionOptional = competitionRepository.findById(competitionId);
        CompetitionDTO competitionDTO = competitionOptional
                .map(this::mapEntityToDTO)
                .orElseThrow(() -> new ValidationException("Competition not found with id: " + competitionId));

        return competitionDTO;
    }


    @Override
    public Competition updateCompetition(Long competitionId, CompetitionDTO competitionDTO) {
        Competition existingCompetition = mapDTOToEntity(getCompetitionById(competitionId));

        if (!isCodeValid(competitionDTO.getCode(), competitionDTO.getLocation(), competitionDTO.getDate())){
            throw new ValidationException("Invalid code format! should be like this ex: Imsouane: pattern: ims-22-12-23.");
        }

        existingCompetition.setCode(competitionDTO.getCode());
        existingCompetition.setDate(competitionDTO.getDate());
        existingCompetition.setStartTime(competitionDTO.getStartTime());
        existingCompetition.setEndTime(competitionDTO.getEndTime());
        existingCompetition.setLocation(competitionDTO.getLocation());
        existingCompetition.setAmount(competitionDTO.getAmount());

        return competitionRepository.save(existingCompetition);
    }

    @Override
    public void deleteCompetition(Long competitionId) {
        Competition existingCompetition = mapDTOToEntity(getCompetitionById(competitionId));
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
                .limitParticipants(competitionDTO.getLimitParticipants())
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
                .limitParticipants(competition.getLimitParticipants())
                .location(competition.getLocation())
                .amount(competition.getAmount())
                .build();
    }
}
