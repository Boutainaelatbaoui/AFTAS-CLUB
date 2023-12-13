package com.aftas.aftasbackend.controller;

import com.aftas.aftasbackend.model.dto.FishDTO;
import com.aftas.aftasbackend.model.entities.Fish;
import com.aftas.aftasbackend.service.IFishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fishes")
public class FishController {

    private final IFishService fishService;

    @Autowired
    public FishController(IFishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FishDTO>> getAllFishes() {
        List<FishDTO> fishes = fishService.getAllFishes();
        return new ResponseEntity<>(fishes, HttpStatus.OK);
    }

    @GetMapping("/{fishId}")
    public ResponseEntity<Fish> getFishById(@PathVariable Long fishId) {
        Fish fish = fishService.getFishById(fishId);
        return new ResponseEntity<>(fish, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<FishDTO> createFish(@RequestBody @Valid FishDTO fishDTO) {
        FishDTO createdFish = fishService.createFish(fishDTO);
        return new ResponseEntity<>(createdFish, HttpStatus.CREATED);
    }

    @PutMapping("/{fishId}")
    public ResponseEntity<FishDTO> updateFish(@PathVariable Long fishId, @RequestBody @Valid FishDTO fishDTO) {
        FishDTO updatedFish = fishService.updateFish(fishId, fishDTO);
        return new ResponseEntity<>(updatedFish, HttpStatus.OK);
    }

    @DeleteMapping("/{fishId}")
    public ResponseEntity<String> deleteFish(@PathVariable Long fishId) {
        fishService.deleteFish(fishId);
        return new ResponseEntity<>("Fish has been deleted.", HttpStatus.NO_CONTENT);
    }
}
