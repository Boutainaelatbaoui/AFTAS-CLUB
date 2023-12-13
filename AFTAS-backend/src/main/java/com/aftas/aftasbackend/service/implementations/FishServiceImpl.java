package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.model.dto.FishDTO;
import com.aftas.aftasbackend.model.entities.Fish;
import com.aftas.aftasbackend.repository.FishRepository;
import com.aftas.aftasbackend.repository.LevelRepository;
import com.aftas.aftasbackend.service.IFishService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FishServiceImpl implements IFishService {

    private final FishRepository fishRepository;
    private final LevelRepository levelRepository;

    @Autowired
    public FishServiceImpl(FishRepository fishRepository, LevelRepository levelRepository) {
        this.fishRepository = fishRepository;
        this.levelRepository = levelRepository;
    }

    @Override
    public List<FishDTO> getAllFishes() {
        List<Fish> fishes = fishRepository.findAll();
        return fishes.stream()
                .map(this::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Fish getFishById(Long fishId) {
        return fishRepository.findById(fishId)
                .orElseThrow(() -> new ValidationException("Fish not found with id: " + fishId));
    }

    @Override
    public FishDTO createFish(FishDTO fishDTO) {
        validateLevelId(fishDTO.getLevel_id());

        if (fishRepository.existsByName(fishDTO.getName())) {
            throw new ValidationException("The name is already exists.");
        }

        Fish fish = mapDTOToEntity(fishDTO);
        Fish savedFish = fishRepository.save(fish);
        return mapEntityToDTO(savedFish);
    }

    @Override
    public FishDTO updateFish(Long fishId, FishDTO fishDTO) {
        Fish existingFish = getFishById(fishId);
        validateLevelId(fishDTO.getLevel_id());

        if (!existingFish.getName().equals(fishDTO.getName())) {
            if (fishRepository.existsByName(fishDTO.getName())) {
                throw new ValidationException("The name is already exists.");
            }
        }

        existingFish.setName(fishDTO.getName());
        existingFish.setAverageWeight(fishDTO.getAverageWeight());
        existingFish.setLevel(levelRepository.getById(fishDTO.getLevel_id()));

        Fish updatedFish = fishRepository.save(existingFish);
        return mapEntityToDTO(updatedFish);
    }

    private void validateLevelId(Long levelId) {
        if (!levelRepository.existsById(levelId)) {
            throw new ValidationException("Invalid Level ID. Level does not exist.");
        }
    }

    @Override
    public void deleteFish(Long fishId) {
        Fish existingFish = getFishById(fishId);
        fishRepository.delete(existingFish);
    }

    private FishDTO mapEntityToDTO(Fish fish) {
        return FishDTO.builder()
                .id(fish.getId())
                .name(fish.getName())
                .averageWeight(fish.getAverageWeight())
                .level_id(fish.getLevel().getId())
                .build();
    }

    private Fish mapDTOToEntity(FishDTO fishDTO) {
        return Fish.builder()
                .name(fishDTO.getName())
                .averageWeight(fishDTO.getAverageWeight())
                .level(levelRepository.getById(fishDTO.getLevel_id()))
                .build();
    }
}
