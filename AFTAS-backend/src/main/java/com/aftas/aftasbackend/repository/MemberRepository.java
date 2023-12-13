package com.aftas.aftasbackend.repository;

import com.aftas.aftasbackend.model.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByNumAndNameAndFamilyName(Integer num, String name, String familyName);
}

