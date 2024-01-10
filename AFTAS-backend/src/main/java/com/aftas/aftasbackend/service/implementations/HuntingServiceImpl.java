package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.model.dto.HuntingDTO;
import com.aftas.aftasbackend.model.entities.*;
import com.aftas.aftasbackend.repository.*;
import com.aftas.aftasbackend.service.IHuntingService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HuntingServiceImpl implements IHuntingService {
    private final HuntingRepository huntingRepository;
    private final FishRepository fishRepository;
    private final CompetitionRepository competitionRepository;
    private final MemberRepository memberRepository;
    private final RankingRepository rankingRepository;

    @Autowired
    public HuntingServiceImpl(HuntingRepository huntingRepository, FishRepository fishRepository,
                              CompetitionRepository competitionRepository, MemberRepository memberRepository, RankingRepository rankingRepository) {
        this.huntingRepository = huntingRepository;
        this.fishRepository = fishRepository;
        this.competitionRepository = competitionRepository;
        this.memberRepository = memberRepository;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public Hunting createHunting(HuntingDTO huntingDTO) {
        Member member = memberRepository.findById(huntingDTO.getMemberId())
                .orElseThrow(() -> new ValidationException("Member not found with ID: " + huntingDTO.getMemberId()));

        Fish fish = fishRepository.findById(huntingDTO.getFishId())
                .orElseThrow(() -> new ValidationException("Fish not found with ID: " + huntingDTO.getFishId()));

        Competition competition = competitionRepository.findById(huntingDTO.getCompetitionId())
                .orElseThrow(() -> new ValidationException("Competition not found with ID: " + huntingDTO.getCompetitionId()));

        if (huntingDTO.getWeight() == null || huntingDTO.getWeight() < fish.getAverageWeight()) {
            throw new ValidationException("Entered weight should be greater than or equal to the average weight of the fish");
        }

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime competitionStartDateTime = LocalDateTime.of(competition.getDate(), competition.getStartTime());
        LocalDateTime competitionEndDateTime = LocalDateTime.of(competition.getDate(), competition.getEndTime()).plusHours(2);

        if (currentDate.isBefore(competitionStartDateTime) || currentDate.isAfter(competitionEndDateTime)) {
            throw new ValidationException("Hunt can only be created before the competition starts and up to 2 hours after it ends");
        }

        Optional<Ranking> rankingOptional = rankingRepository.findByCompetitionAndMember(competition, member);

        if (rankingOptional.isPresent()) {
            Hunting existingHunting = huntingRepository.findByFishAndCompetitionAndMember(
                    huntingDTO.getFishId(), huntingDTO.getCompetitionId(), huntingDTO.getMemberId());

            if (existingHunting != null) {
                existingHunting.setNumberOfFish(existingHunting.getNumberOfFish() + 1);
                updateScoreInRanking(existingHunting, rankingOptional.get());
                return huntingRepository.save(existingHunting);
            } else {
                Hunting newHunting = mapDtoToEntity(huntingDTO);
                newHunting.setNumberOfFish(1);
                updateScoreInRanking(newHunting, rankingOptional.get());
                return huntingRepository.save(newHunting);
            }
        } else {
            throw new ValidationException("Member is not part of the competition");
        }
    }

    public void updateScoreInRanking(Hunting hunting, Ranking ranking) {
        int fishLevel = hunting.getFish().getLevel().getPoints();
        ranking.setScore(ranking.getScore() + fishLevel);
        ranking.setRank(0);
        rankingRepository.save(ranking);
    }


    public List<HuntingDTO> getAllHuntings() {
        List<Hunting> huntings = huntingRepository.findAll();
        return huntings.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public HuntingDTO getHuntingById(Long huntingId) {
        Hunting hunting = huntingRepository.findById(huntingId)
                .orElseThrow(() -> new ValidationException("Hunting not found with id: " + huntingId));
        return mapEntityToDto(hunting);
    }

    private Hunting mapDtoToEntity(HuntingDTO huntingDTO) {
        return Hunting.builder()
                .numberOfFish(huntingDTO.getNumberOfFish())
                .fish(fishRepository.getById(huntingDTO.getFishId()))
                .competition(competitionRepository.getById(huntingDTO.getCompetitionId()))
                .member(memberRepository.getById(huntingDTO.getMemberId()))
                .weight(huntingDTO.getWeight())
                .build();
    }

    public HuntingDTO mapEntityToDto(Hunting hunting) {
        return HuntingDTO.builder()
                .id(hunting.getId())
                .numberOfFish(hunting.getNumberOfFish())
                .fish(hunting.getFish())
                .competition(hunting.getCompetition())
                .member(hunting.getMember())
                .memberId(hunting.getMember().getId())
                .fishId(hunting.getFish().getId())
                .competitionId(hunting.getCompetition().getId())
                .weight(hunting.getWeight())
                .build();
    }
}
