package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.model.dto.HuntingDTO;
import com.aftas.aftasbackend.model.entities.Competition;
import com.aftas.aftasbackend.model.entities.Fish;
import com.aftas.aftasbackend.model.entities.Hunting;
import com.aftas.aftasbackend.model.entities.Member;
import com.aftas.aftasbackend.repository.CompetitionRepository;
import com.aftas.aftasbackend.repository.FishRepository;
import com.aftas.aftasbackend.repository.HuntingRepository;
import com.aftas.aftasbackend.repository.MemberRepository;
import com.aftas.aftasbackend.service.IHuntingService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HuntingServiceImpl implements IHuntingService {
    private final HuntingRepository huntingRepository;
    private final FishRepository fishRepository;
    private final CompetitionRepository competitionRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public HuntingServiceImpl(HuntingRepository huntingRepository, FishRepository fishRepository,
                              CompetitionRepository competitionRepository, MemberRepository memberRepository) {
        this.huntingRepository = huntingRepository;
        this.fishRepository = fishRepository;
        this.competitionRepository = competitionRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Hunting createHunting(HuntingDTO huntingDTO) {
        if (!competitionRepository.existsById(huntingDTO.getCompetitionId()) ||
                !fishRepository.existsById(huntingDTO.getFishId()) ||
                !memberRepository.existsById(huntingDTO.getMemberId())) {
            throw new ValidationException("Invalid competition, fish, or member ID");
        }

        Fish fish = fishRepository.findById(huntingDTO.getFishId())
                .orElseThrow(() -> new ValidationException("Fish not found with ID: " + huntingDTO.getFishId()));

        if (huntingDTO.getWeight() == null || huntingDTO.getWeight() < fish.getAverageWeight()) {
            throw new ValidationException("Entered weight should be greater than or equal to the average weight of the fish");
        }

        Hunting existingHunting = huntingRepository.findByFishAndCompetitionAndMember(
                huntingDTO.getFishId(), huntingDTO.getCompetitionId(), huntingDTO.getMemberId());

        if (existingHunting != null) {
            existingHunting.setNumberOfFish(existingHunting.getNumberOfFish() + 1);
            return huntingRepository.save(existingHunting);
        } else {
            Hunting newHunting = mapDtoToEntity(huntingDTO);
            newHunting.setNumberOfFish(1);
            return huntingRepository.save(newHunting);
        }
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
