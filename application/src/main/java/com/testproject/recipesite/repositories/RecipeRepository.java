package com.testproject.recipesite.repositories;

import com.testproject.recipesite.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
