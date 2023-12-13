package com.aftas.aftasbackend.service;

import com.aftas.aftasbackend.model.dto.FishDTO;
import com.aftas.aftasbackend.model.entities.Fish;

import java.util.List;

public interface IFishService {
    List<FishDTO> getAllFishes();
    Fish getFishById(Long fishId);
    FishDTO createFish(FishDTO fishDTO);
    FishDTO updateFish(Long fishId, FishDTO fishDTO);
    void deleteFish(Long fishId);
}
