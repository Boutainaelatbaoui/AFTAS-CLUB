package com.aftas.aftasbackend.service.implementations;

import com.aftas.aftasbackend.mapper.LevelMapper;
import com.aftas.aftasbackend.model.dto.LevelDTO;
import com.aftas.aftasbackend.model.entities.Level;
import com.aftas.aftasbackend.repository.LevelRepository;
import com.aftas.aftasbackend.service.ILevelService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LevelServiceImpl implements ILevelService {

    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository, LevelMapper levelMapper) {
        this.levelRepository = levelRepository;
        this.levelMapper = levelMapper;
    }

    @Override
    public List<LevelDTO> getAllLevels() {
        List<Level> levels = levelRepository.findAll();
        return levels.stream()
                .map(levelMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Level createLevel(LevelDTO levelDTO) {
        Integer code = initializeCode();
        Integer points = calculatePoints(code);

        levelDTO.setCode(code);
        levelDTO.setPoints(points);

        Level level = levelMapper.mapToEntity(levelDTO);
        return levelRepository.save(level);
    }
    private Integer initializeCode() {
        Optional<Integer> maxCode = levelRepository.findMaxCode();
        return maxCode.orElse(0) + 1;
    }

    private Integer calculatePoints(Integer code) {
        return code * 10;
    }

    @Override
    public Level getLevelById(Long levelId) {
        return levelRepository.findById(levelId)
                .orElseThrow(() -> new ValidationException("Level not found with id: " + levelId));
    }

    @Override
    public void deleteLevel(Long levelId) {
        Level existingLevel = getLevelById(levelId);
        levelRepository.delete(existingLevel);
    }

    @Override
    public LevelDTO updateLevel(Long levelId, LevelDTO levelDTO) {
        Level existingLevel = getLevelById(levelId);

        if (!existingLevel.getCode().equals(levelDTO.getCode())) {
            if (levelRepository.existsByCode(levelDTO.getCode())) {
                throw new ValidationException("The code already exists.");
            }
        }
        existingLevel.setCode(levelDTO.getCode());
        existingLevel.setDescription(levelDTO.getDescription());
        existingLevel.setPoints(calculatePoints(levelDTO.getCode()));
        return levelMapper.mapToDTO(levelRepository.save(existingLevel));
    }
}
