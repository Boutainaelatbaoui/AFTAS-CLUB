package com.aftas.aftasbackend.repository;

import com.aftas.aftasbackend.model.entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HuntingRepository extends JpaRepository<Hunting, Long> {

    @Query("SELECT h FROM Hunting h WHERE h.fish.id = :fishId AND h.competition.id = :competitionId AND h.member.id = :memberId")
    Hunting findByFishAndCompetitionAndMember(@Param("fishId") Long fishId, @Param("competitionId") Long competitionId, @Param("memberId") Long memberId);
}
