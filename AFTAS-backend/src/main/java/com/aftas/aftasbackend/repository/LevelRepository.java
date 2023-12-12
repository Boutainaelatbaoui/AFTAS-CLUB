package com.aftas.aftasbackend.repository;

import com.aftas.aftasbackend.model.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
}

