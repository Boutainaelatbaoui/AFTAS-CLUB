package com.aftas.aftasbackend.repository;

import com.aftas.aftasbackend.model.entities.Competition;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    List<Competition> findCompetitionByDate(LocalDate date);
    boolean existsByCode(String code);
    List<Competition> findAll();
    Page<Competition> findAll(Pageable pageable);
}
