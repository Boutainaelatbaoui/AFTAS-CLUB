package com.aftas.aftasbackend.service;

public interface IManagerService {
    void activateUser(Integer userId);
    void deactivateUser(Integer userId);
    void updateUserRole(Integer userId, Long roleId);
}
