package com.aftas.aftasbackend.repository;

import com.aftas.aftasbackend.enums.RoleName;
import com.aftas.aftasbackend.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
