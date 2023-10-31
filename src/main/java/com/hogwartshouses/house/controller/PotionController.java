package com.hogwartshouses.house.controller;

import com.hogwartshouses.house.model.classes.Potion;
import com.hogwartshouses.house.model.classes.Room;
import com.hogwartshouses.house.service.PotionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("potions")
public class PotionController {

    private final PotionService potionService;

    public PotionController(PotionService potionService) {
        this.potionService = potionService;
    }

    @GetMapping
    public List<Potion> getAllPotions() {
        return potionService.getAllPotions();
    }

    @PostMapping
    public Potion postNewPotion(@RequestBody Potion potion) {

        List<Potion> potionList = potionService.getAllPotions();
        return potionService.savePotion(potion, potionList);
    }

}
