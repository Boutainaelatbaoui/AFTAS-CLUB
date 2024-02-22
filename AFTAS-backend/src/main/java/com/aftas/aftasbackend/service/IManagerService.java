package com.aftas.aftasbackend.service;

import com.aftas.aftasbackend.model.entities.Role;

import java.util.List;

public interface IManagerService {
    void activateUser(Integer userId);
    void deactivateUser(Integer userId);
    void updateUserRole(Integer userId, Long roleId);
    List<Role> getAllRoles();
}
