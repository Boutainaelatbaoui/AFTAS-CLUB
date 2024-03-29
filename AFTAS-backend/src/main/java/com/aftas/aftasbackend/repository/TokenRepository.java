package com.aftas.aftasbackend.repository;

import com.aftas.aftasbackend.model.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      SELECT t FROM Token t
      INNER JOIN t.user u
      WHERE u.id = :userId
        AND (t.expired = false OR t.revoked = false)
      """)
    List<Token> findAllValidTokensByUserId(@Param("userId") Long userId);
    Optional<Token> findByToken(String token);
}

