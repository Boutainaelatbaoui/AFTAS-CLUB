package com.aftas.aftasbackend.controller;


import com.aftas.aftasbackend.service.IManagerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Transactional
@CrossOrigin(origins = "http://localhost:4200")
public class ManagementController {
    private final IManagerService managerService;

    @PutMapping("/activate/{userId}")
    @PreAuthorize("hasAuthority('CAN_MANAGE_USERS')")
    public ResponseEntity<String> activateUser(@PathVariable Integer userId){
        managerService.activateUser(userId);
        return ResponseEntity.ok("User activated");
    }


    @PutMapping("/deactivate/{userId}")
    @PreAuthorize("hasAuthority('CAN_MANAGE_USERS')")
    public ResponseEntity<String> deactivateUser(@PathVariable Integer userId){
        managerService.deactivateUser(userId);
        return ResponseEntity.ok("User deactivated");
    }


    @PutMapping("/updateRole/{userId}/{roleId}")
    @PreAuthorize("hasAuthority('CAN_MANAGE_USERS')")
    public ResponseEntity<String> updateUserRole(@PathVariable Integer userId, @PathVariable Long roleId){
        managerService.updateUserRole(userId, roleId);
        return ResponseEntity.ok("User role updated");
    }




}
