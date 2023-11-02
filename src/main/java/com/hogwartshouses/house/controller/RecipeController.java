package com.hogwartshouses.house.controller;

import com.hogwartshouses.house.model.classes.Person;
import com.hogwartshouses.house.model.classes.Potion;
import com.hogwartshouses.house.model.classes.Recipe;
import com.hogwartshouses.house.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("recipes")
public class RecipeController {

    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    Optional<Recipe> findSingleRecipe(@PathVariable Long id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping("/{id}")
    public Recipe postRecipe(@RequestBody Recipe recipe, @PathVariable Long id){
        return recipeService.saveRecipe(recipe, id);
    }
}
