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

    @PostMapping("/{id}")
    public Potion postNewPotion(@RequestBody Potion potion, @PathVariable Long id) {

        List<Potion> potionList = potionService.getAllPotions();
        return potionService.savePotion(potion, potionList, id);
    }

    @GetMapping("/{id}")
    public List<Potion> getPotionsByStudent(@PathVariable Long id) {
        return potionService.getPotionsByStudent(id);
    }


    @PatchMapping("/{id}")
    public Potion updatePotion(@PathVariable Long id, @RequestBody Potion potion) {
        return potionService.changePotion(id, potion);
    }
}
