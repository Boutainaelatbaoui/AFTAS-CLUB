package com.aftas.aftasbackend.repository;

import com.aftas.aftasbackend.model.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    boolean existsByCode(Integer code);
    @Query("SELECT MAX(l.code) FROM Level l")
    Optional<Integer> findMaxCode();
    @Query("SELECT l.points FROM Level l WHERE l.code = :code")
    Integer findPointsByCode(@Param("code") Integer code);
}

