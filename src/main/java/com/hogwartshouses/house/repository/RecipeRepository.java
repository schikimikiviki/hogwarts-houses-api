package com.hogwartshouses.house.repository;

import com.hogwartshouses.house.model.classes.Recipe;
import com.hogwartshouses.house.model.classes.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
