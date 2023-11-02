package com.hogwartshouses.house.service;

import com.hogwartshouses.house.model.classes.Person;
import com.hogwartshouses.house.model.classes.Potion;
import com.hogwartshouses.house.model.classes.Recipe;
import com.hogwartshouses.house.model.enums.BrewingStatus;
import com.hogwartshouses.house.repository.PersonRepository;
import com.hogwartshouses.house.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final PersonRepository personRepository;

    public RecipeService(RecipeRepository recipeRepository, PersonRepository personRepository) {
        this.recipeRepository = recipeRepository;
        this.personRepository = personRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipe(Long id) {
        return recipeRepository.findById(id);
    }

    public Recipe saveRecipe(Recipe recipe, Long id) {

        Person person = personRepository.findById(id).orElse(null);

        if (person != null) {
            recipe.setPerson(person);

            if (recipe.getPerson() != null) {
                recipe.setPerson(recipe.getPerson());
            }

            return recipeRepository.save(recipe);
        } else {
            return null;
        }
    }
}
