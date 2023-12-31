package com.hogwartshouses.house.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hogwartshouses.house.model.enums.IngredientName;
import jakarta.persistence.*;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private IngredientName ingredientName;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "potion_id")
    private Potion potion;
    public Ingredient() {
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IngredientName getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(IngredientName ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }
}
