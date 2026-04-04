package com.testproject.recipesite.controllers;

import com.testproject.recipesite.models.Recipe;
import com.testproject.recipesite.repositories.RecipeRepository;
import com.testproject.recipesite.services.RecipeService;
import com.testproject.recipesite.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private S3Service s3Service;

    @GetMapping("/{id}")
    public String getRecipeById(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getById(id);

        model.addAttribute("recipe", recipe);
        return "recipe-details";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "create-recipe";
    }
    @PostMapping("/save")
    public String saveRecipe(@ModelAttribute("recipe")  Recipe recipe,
                             @RequestParam String ingredientsText,
                             @RequestParam String stepsText,
                             @RequestParam("image") MultipartFile image
    ) {

        String imageUrl = s3Service.uploadImage(image);

        recipe.setIngredients(List.of(ingredientsText.split("\\R")));
        recipe.setSteps(List.of(stepsText.split("\\R")));
        recipe.setImageUrl(imageUrl);

        recipeService.save(recipe);
        return "redirect:/";

    }

    @GetMapping("/{id}/delete")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        recipeService.deleteViaId(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editRecipe(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getById(id);

        model.addAttribute("recipe", recipe);


        model.addAttribute("ingredientsText", String.join("\n", recipe.getIngredients()));
        model.addAttribute("stepsText", String.join("\n", recipe.getSteps()));

        return "recipe-edit";
    }

    @PostMapping("/{id}")
    public String updateRecipe(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Integer cookingTime,
            @RequestParam String ingredientsText,
            @RequestParam String stepsText,
            @RequestParam("image") MultipartFile image
    ) {
        Recipe recipe = recipeService.getById(id);
    
        recipe.setTitle(title);
        recipe.setDescription(description);
        recipe.setCookingTime(cookingTime);
    
        recipe.setIngredients(new ArrayList<>(List.of(ingredientsText.split("\\R"))));
        recipe.setSteps(new ArrayList<>(List.of(stepsText.split("\\R"))));
    
        if (image != null && !image.isEmpty()) {
            String imageUrl = s3Service.uploadImage(image);
            recipe.setImageUrl(imageUrl);
        }
    
        recipeService.save(recipe);
    
        return "redirect:/recipes/" + id;
    }





}
