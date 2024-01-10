package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.model.dto.HuntingDTO;
import com.aftas.aftasbackend.model.entities.*;
import com.aftas.aftasbackend.repository.*;
import jakarta.validation.ValidationException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class HuntingServiceImplTest {
    @Mock
    private HuntingRepository huntingRepository;

    @Mock
    private FishRepository fishRepository;

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private RankingRepository rankingRepository;

    @Mock
    private LevelRepository levelRepository;

    @InjectMocks
    private HuntingServiceImpl huntingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testHuntingWeight() {
        Long memberId = 1L;
        Long fishId = 2L;
        Long competitionId = 3L;
        Double weight = 10.0;

        HuntingDTO huntingDTO = HuntingDTO.builder()
                .memberId(memberId)
                .fishId(fishId)
                .competitionId(competitionId)
                .weight(weight)
                .build();

        Member member = Member.builder()
                .id(memberId)
                .build();

        Fish fish = Fish.builder()
                .id(fishId)
                .averageWeight(15.0)
                .build();

        Competition competition = Competition.builder()
                .id(competitionId)
                .build();

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(fishRepository.findById(fishId)).thenReturn(Optional.of(fish));
        when(competitionRepository.findById(competitionId)).thenReturn(Optional.of(competition));

        ValidationException exception = assertThrows(ValidationException.class, () -> huntingService.createHunting(huntingDTO));

        assertEquals("Entered weight should be greater than or equal to the average weight of the fish", exception.getMessage());
    }

    @Test
    void testUpdateScoreExistingHunt() {
        Hunting hunting = Hunting.builder()
                .fish(Fish.builder().id(1L).level(Level.builder().id(1L).code(1).points(10).build()).build())
                .competition(Competition.builder().id(1L).build())
                .member(Member.builder().id(1L).build())
                .build();

        Ranking ranking = new Ranking();
        ranking.setRank(1);
        ranking.setScore(10);
        ranking.setMember(Member.builder().id(1L).build());
        ranking.setCompetition(Competition.builder().id(1L).build());

        when(huntingRepository.findByFishAndCompetitionAndMember(
                hunting.getFish().getId(), hunting.getCompetition().getId(), hunting.getMember().getId()))
                .thenReturn(hunting);
        when(rankingRepository.findByCompetitionAndMember(hunting.getCompetition(), hunting.getMember()))
                .thenReturn(Optional.of(ranking));

        huntingService.updateScoreInRanking(hunting, ranking);

        verify(rankingRepository, times(1)).save(ranking);
        assertEquals(20, ranking.getScore());
    }

    @Test
    void testUpdateScoreNewHunt() {
        Hunting hunting = Hunting.builder()
                .fish(Fish.builder().id(1L).level(Level.builder().id(1L).code(1).points(10).build()).build())
                .competition(Competition.builder().id(1L).build())
                .member(Member.builder().id(1L).build())
                .build();

        Ranking ranking = new Ranking();
        ranking.setRank(0);
        ranking.setScore(0);
        ranking.setMember(Member.builder().id(1L).build());
        ranking.setCompetition(Competition.builder().id(1L).build());

        when(huntingRepository.findByFishAndCompetitionAndMember(
                hunting.getFish().getId(), hunting.getCompetition().getId(), hunting.getMember().getId()))
                .thenReturn(null);
        when(rankingRepository.findByCompetitionAndMember(hunting.getCompetition(), hunting.getMember()))
                .thenReturn(Optional.of(ranking));

        huntingService.updateScoreInRanking(hunting, ranking);

        verify(rankingRepository, times(1)).save(ranking);
        assertEquals(10, ranking.getScore());
    }

    @Test
    void TestUpdateScoreInRanking() {
        Hunting existingHunting = mock(Hunting.class);
        Fish fish = mock(Fish.class);
        Level level = mock(Level.class);
        Ranking ranking = mock(Ranking.class);

        when(existingHunting.getFish()).thenReturn(fish);
        when(fish.getLevel()).thenReturn(level);
        when(level.getPoints()).thenReturn(10);
        when(ranking.getScore()).thenReturn(20);

        when(rankingRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0, Ranking.class));

        huntingService.updateScoreInRanking(existingHunting, ranking);

        verify(ranking).setScore(30);
        verify(ranking).setRank(0);
        verify(rankingRepository, times(1)).save(any());
    }

}