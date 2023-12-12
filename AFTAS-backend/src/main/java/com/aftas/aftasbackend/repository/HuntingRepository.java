package com.aftas.aftasbackend.repository;

import com.aftas.aftasbackend.model.entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {
}
