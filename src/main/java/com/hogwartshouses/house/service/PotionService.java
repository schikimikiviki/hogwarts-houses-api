package com.hogwartshouses.house.service;

import com.hogwartshouses.house.model.classes.Ingredient;
import com.hogwartshouses.house.model.classes.Person;
import com.hogwartshouses.house.model.classes.Potion;
import com.hogwartshouses.house.model.enums.BrewingStatus;
import com.hogwartshouses.house.repository.PersonRepository;
import com.hogwartshouses.house.repository.PotionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PotionService {

    private final PotionRepository potionRepository;
    private final PersonRepository personRepository;

    public PotionService(PotionRepository potionRepository, PersonRepository personRepository) {
        this.potionRepository = potionRepository;
        this.personRepository = personRepository;
    }

    public List<Potion> getAllPotions() {
        return potionRepository.findAll();
    }

    public Potion savePotion(Potion potion, List<Potion> potionList, Long id) {

        Person person = personRepository.findById(id).orElse(null);

        if (person != null) {
            potion.setPerson(person);
            boolean isReplica = false;

            for (Potion potionItem : potionList) {
                if (areIngredientListsIdentical(potionItem.getIngredientList(), potion.getIngredientList())) {
                    potion.setBrewingStatus(BrewingStatus.replica);
                    isReplica = true;
                    break;
                }
            }

            if (!isReplica) {
                if (potion.getIngredientList().size() < 5) {
                    potion.setBrewingStatus(BrewingStatus.brew);
                } else {
                    potion.setBrewingStatus(BrewingStatus.discovery);
                }
            }

//            if (potion.getPerson() != null) {
//                potion.setPerson(potion.getPerson());
//            }

            return potionRepository.save(potion);
        } else {
            return null;
        }


    }


    private boolean areIngredientListsIdentical(List<Ingredient> list1, List<Ingredient> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        List<String> names1 = list1.stream()
                .map(Ingredient::getIngredientName)
                .map(Enum::name)
                .sorted()
                .collect(Collectors.toList());

        List<String> names2 = list2.stream()
                .map(Ingredient::getIngredientName)
                .map(Enum::name)
                .sorted()
                .collect(Collectors.toList());

        return names1.equals(names2);
    }

    public List<Potion> getPotionsByStudent(Long id) {
        //get student via id
        List<Potion> potionList = potionRepository.findByPersonId(id);
        return potionList;
    }

    //todo: posting recipe to person
    // why is brewing status a number ? lol



}
