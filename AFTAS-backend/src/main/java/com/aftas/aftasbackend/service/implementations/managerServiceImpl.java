package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.model.entities.Role;
import com.aftas.aftasbackend.repository.RoleRepository;
import com.aftas.aftasbackend.repository.UserRepository;
import com.aftas.aftasbackend.service.IManagerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class managerServiceImpl implements IManagerService {
    private final MemberServiceImpl memberService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void activateUser(Integer userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
    }


    @Override
    public void deactivateUser(Integer userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public void updateUserRole(Integer userId, Long roleId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        var newRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        user.setRole(newRole);
        userRepository.save(user);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
