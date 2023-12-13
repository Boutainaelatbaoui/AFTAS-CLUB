package com.aftas.aftasbackend.repository;

import com.aftas.aftasbackend.model.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT MAX(m.num) FROM Member m")
    Optional<Integer> findMaxNum();
    boolean existsByIdentityNumber(String identityNumber);
    @Query("SELECT m FROM Member m WHERE :num is null or m.num = :num")
    List<Member> searchByNum(@Param("num") Integer num);
    @Query("SELECT m FROM Member m WHERE :name is null or m.name like %:name%")
    List<Member> searchByName(@Param("name") String name);
    @Query("SELECT m FROM Member m WHERE :familyName is null or m.familyName like %:familyName%")
    List<Member> searchByFamilyName(@Param("familyName") String familyName);
}

