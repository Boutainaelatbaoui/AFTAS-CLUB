package com.aftas.aftasbackend.service;

import com.aftas.aftasbackend.model.dto.HuntingDTO;
import com.aftas.aftasbackend.model.entities.Hunting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IHuntingService {
    Hunting createHunting(HuntingDTO hunting);
    List<HuntingDTO> getAllHuntings();
    HuntingDTO getHuntingById(Long huntingId);
}

