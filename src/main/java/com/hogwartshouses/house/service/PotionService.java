package com.hogwartshouses.house.service;

import com.hogwartshouses.house.model.classes.Potion;
import com.hogwartshouses.house.model.classes.Room;
import com.hogwartshouses.house.model.enums.BrewingStatus;
import com.hogwartshouses.house.repository.PotionRepository;
import com.hogwartshouses.house.service.exceptions.RoomCapacityFullException;
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

    public Potion savePotion(Potion potion, List<Potion> potionList) {

        // check if ingredients are there already, get all potions

        for (Potion potionItem : potionList){
            if (potionItem.getIngredientList().equals(potion.getIngredientList())){
                potion.setBrewingStatus(BrewingStatus.replica);
            }
        }

        if (potion.getIngredientList().size() < 5){
            potion.setBrewingStatus(BrewingStatus.brew);
        } else {
            potion.setBrewingStatus(BrewingStatus.discovery);
        }
        return potionRepository.save(potion);
    }

}
