package com.aftas.aftasbackend.controller;

import com.aftas.aftasbackend.model.dto.LevelDTO;
import com.aftas.aftasbackend.model.entities.Level;
import com.aftas.aftasbackend.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/levels")
public class LevelController {

    private final ILevelService levelService;

    @Autowired
    public LevelController(ILevelService levelService) {
        this.levelService = levelService;
    }

    @PostMapping("")
    public ResponseEntity<Level> createLevel(@RequestBody @Valid LevelDTO levelDTO) {
        Level createdLevel = levelService.createLevel(levelDTO);
        return new ResponseEntity<>(createdLevel, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LevelDTO>> getAllLevels() {
        List<LevelDTO> levels = levelService.getAllLevels();
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @GetMapping("/{levelId}")
    public ResponseEntity<Level> getLevelById(@PathVariable Long levelId) {
        return new ResponseEntity<>(levelService.getLevelById(levelId), HttpStatus.OK);
    }

    @PutMapping("/{levelId}")
    public ResponseEntity<LevelDTO> updateLevel(
            @PathVariable Long levelId,
            @RequestBody @Valid LevelDTO levelDTO) {
        LevelDTO updatedLevel = levelService.updateLevel(levelId, levelDTO);
        return new ResponseEntity<>(updatedLevel, HttpStatus.OK);
    }

    @DeleteMapping("/{levelId}")
    public ResponseEntity<String> deleteLevel(@PathVariable Long levelId) {
        levelService.deleteLevel(levelId);
        return new ResponseEntity<>("The Level has been deleted.", HttpStatus.NO_CONTENT);
    }
}
