package com.testproject.recipesite.services;

import com.testproject.recipesite.models.Recipe;
import com.testproject.recipesite.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;


    public List<Recipe> getAllRecipe(){
        return recipeRepository.findAll();
    }

    public Recipe getById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found for id : " + id
                        )
                );
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void deleteViaId(long id) {
        recipeRepository.deleteById(id);
    }

}
