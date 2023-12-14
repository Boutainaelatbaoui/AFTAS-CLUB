package com.aftas.aftasbackend.controller;

import com.aftas.aftasbackend.model.dto.HuntingDTO;
import com.aftas.aftasbackend.model.entities.Hunting;
import com.aftas.aftasbackend.service.IHuntingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/huntings")
public class HuntingController {

    private final IHuntingService huntingService;

    @Autowired
    public HuntingController(IHuntingService huntingService) {
        this.huntingService = huntingService;
    }

    @PostMapping("")
    public ResponseEntity<String> createHunting(@RequestBody HuntingDTO hunting) {
        Hunting result = huntingService.createHunting(hunting);
        return new ResponseEntity<>("The hunting is added successfully/", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HuntingDTO>> getAllHuntings() {
        List<HuntingDTO> huntings = huntingService.getAllHuntings();
        return new ResponseEntity<>(huntings, HttpStatus.OK);
    }

    @GetMapping("/{huntingId}")
    public ResponseEntity<HuntingDTO> getHuntingById(@PathVariable Long huntingId) {
        HuntingDTO hunting = huntingService.getHuntingById(huntingId);
        return new ResponseEntity<>(hunting, HttpStatus.OK);
    }
}
