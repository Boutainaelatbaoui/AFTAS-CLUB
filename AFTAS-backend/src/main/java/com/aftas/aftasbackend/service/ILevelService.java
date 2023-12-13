package com.aftas.aftasbackend.service;

import com.aftas.aftasbackend.model.dto.LevelDTO;
import com.aftas.aftasbackend.model.entities.Level;

import java.util.List;

public interface ILevelService {
    List<LevelDTO> getAllLevels();
    Level createLevel(LevelDTO levelDTO);
    Level getLevelById(Long levelId);
    void deleteLevel(Long levelId);
    LevelDTO updateLevel(Long levelId, LevelDTO levelDTO);
}
