package com.aftas.aftasbackend.controller;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/manager")
@RequiredArgsConstructor
@Transactional
@CrossOrigin(origins = "http://localhost:4200")
public class ManagementController {
    @GetMapping("/jury")
    @PreAuthorize("hasRole('JURY') && hasAnyAuthority('CAN_READ', 'CAN_READ_PARTICIPATIONS', 'CAN_READ_PODIUM_INFO', 'CAN_MANAGE_COMPETITIONS')")
    public String testAdminEndpoint() {
        return "This endpoint is accessible by admins with edit or delete permissions.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('user') && hasAnyAuthority('CAN_READ', 'CAN_READ_PARTICIPATIONS', 'CAN_READ_PODIUM_INFO')")
    public String testUserEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authentication: " + authentication);

        return "This endpoint is accessible by users with add permission.";
    }


    @GetMapping("/manager")
    @PreAuthorize("hasRole('manager') && hasAnyAuthority('CAN_READ', 'CAN_READ_PARTICIPATIONS', 'CAN_READ_PODIUM_INFO', 'CAN_MANAGE_COMPETITIONS', 'CAN_MANAGE_USERS')")
    public String testSuperAdminEndpoint() {
        return "This endpoint is accessible by super admins with edit, add, or delete permissions.";
    }



}
