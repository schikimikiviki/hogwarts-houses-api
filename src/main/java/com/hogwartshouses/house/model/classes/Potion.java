package com.hogwartshouses.house.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hogwartshouses.house.model.enums.BrewingStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Potion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(mappedBy = "potion", orphanRemoval = true)
    private List<Ingredient> ingredientList;

    private BrewingStatus brewingStatus;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;

        if (ingredientList.size() < 5) {
            this.brewingStatus = BrewingStatus.brew;
        }
    }

    public BrewingStatus getBrewingStatus() {
        return brewingStatus;
    }

    public void setBrewingStatus(BrewingStatus brewingStatus) {
        this.brewingStatus = brewingStatus;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
