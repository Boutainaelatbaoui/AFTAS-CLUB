package com.aftas.aftasbackend.repository;


import com.aftas.aftasbackend.enums.Permission;
import com.aftas.aftasbackend.model.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Optional<Privilege> findByName(Permission name);
}
