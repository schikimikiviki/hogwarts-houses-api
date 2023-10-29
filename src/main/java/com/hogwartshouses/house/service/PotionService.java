package com.hogwartshouses.house.service;

import com.hogwartshouses.house.model.classes.Potion;
import com.hogwartshouses.house.repository.PotionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotionService {

    private final PotionRepository potionRepository;

    public PotionService(PotionRepository potionRepository) {
        this.potionRepository = potionRepository;
    }

    public List<Potion> getAllPotions() {
        return potionRepository.findAll();
    }
}
