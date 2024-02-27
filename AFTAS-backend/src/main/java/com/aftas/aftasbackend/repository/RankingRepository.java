package com.aftas.aftasbackend.repository;

import com.aftas.aftasbackend.model.entities.Competition;
import com.aftas.aftasbackend.model.entities.Member;
import com.aftas.aftasbackend.model.entities.Ranking;
import com.aftas.aftasbackend.model.entities.embedded.MemberCompetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
    boolean existsByCompetitionAndMember(Competition competition, Member member);
    Optional<Ranking> findByCompetitionAndMember(Competition competition, Member member);
    List<Ranking> findByCompetitionOrderByScoreDesc(Competition competition);
    List<Ranking> findByMemberId(Long memberId);
}

