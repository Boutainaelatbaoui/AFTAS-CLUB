package com.aftas.aftasbackend.mapper;

import com.aftas.aftasbackend.model.dto.LevelDTO;
import com.aftas.aftasbackend.model.entities.Level;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LevelMapper {

    private final ModelMapper modelMapper;

    public LevelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.createTypeMap(LevelDTO.class, Level.class);
    }

    public LevelDTO mapToDTO(Level level) {
        return modelMapper.map(level, LevelDTO.class);
    }

    public Level mapToEntity(LevelDTO levelDTO) {
        return modelMapper.map(levelDTO, Level.class);
    }
}
