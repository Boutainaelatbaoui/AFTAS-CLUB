package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.model.entities.Competition;
import com.aftas.aftasbackend.model.entities.Ranking;
import com.aftas.aftasbackend.repository.*;
import com.aftas.aftasbackend.service.IRankingService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingServiceImpl implements IRankingService {
    private final CompetitionRepository competitionRepository;
    private final RankingRepository rankingRepository;

    @Autowired
    public RankingServiceImpl(CompetitionRepository competitionRepository, RankingRepository rankingRepository) {
        this.competitionRepository = competitionRepository;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public List<Ranking> rankingsForCompetition(Long competitionId) {
        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new ValidationException("Competition not found with ID: " + competitionId));

        LocalDateTime competitionEndDateTime = LocalDateTime.of(competition.getDate(), competition.getEndTime());
        LocalDateTime twoHoursAfterEnd = competitionEndDateTime.plusHours(2);

        if (LocalDateTime.now().isAfter(twoHoursAfterEnd)) {
            List<Ranking> rankings = rankingRepository.findByCompetitionOrderByScoreDesc(competition);

            boolean rankingsAlreadySet = rankings.stream().anyMatch(ranking -> ranking.getRank() != 0);

            if (!rankingsAlreadySet) {
                for (int i = 0; i < rankings.size(); i++) {
                    rankings.get(i).setRank(i + 1);
                }

                rankingRepository.saveAll(rankings);
            } else {
                throw new ValidationException("Rankings have already been set for this competition.");
            }

            return rankings.stream().limit(3).collect(Collectors.toList());
        } else {
            throw new ValidationException("Rankings can only be updated after 2 hours from the end of the competition.");
        }
    }



    public List<Ranking> getRankingForCompetition(Long competitionId) {
        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new ValidationException("Competition not found with ID: " + competitionId));

        LocalDateTime competitionEndDateTime = LocalDateTime.of(competition.getDate(), competition.getEndTime());
        LocalDateTime twoHoursAfterEnd = competitionEndDateTime.plusHours(2);

        if (LocalDateTime.now().isAfter(twoHoursAfterEnd)) {
            return rankingRepository.findByCompetitionOrderByScoreDesc(competition);
        } else {
            throw new ValidationException("Ranking can only be retrieved after endTime + 2 hours after it ends");
        }
    }

    @Override
    public List<Ranking> getAllRankings() {
        return rankingRepository.findAll();
    }


}
