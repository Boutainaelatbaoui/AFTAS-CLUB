package com.aftas.aftasbackend.mapper;

import com.aftas.aftasbackend.model.dto.FishDTO;
import com.aftas.aftasbackend.model.entities.Fish;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FishMapper {

    private final ModelMapper modelMapper;

    public FishMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.createTypeMap(FishDTO.class, Fish.class);
    }

    public FishDTO mapToDTO(Fish fish) {
        return modelMapper.map(fish, FishDTO.class);
    }

    public Fish mapToEntity(FishDTO fishDTO) {
        return modelMapper.map(fishDTO, Fish.class);
    }
}
