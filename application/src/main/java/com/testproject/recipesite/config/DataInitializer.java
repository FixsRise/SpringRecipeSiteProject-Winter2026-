package com.testproject.recipesite.config;

import com.testproject.recipesite.models.Recipe;
import com.testproject.recipesite.repositories.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRecipes(RecipeRepository recipeRepository) {
        return args -> {

            if (recipeRepository.count() == 0) {

                Recipe recipe1 = new Recipe();
                recipe1.setTitle("Spaghetti Carbonara");
                recipe1.setDescription("A classic Italian pasta dish with eggs, cheese and bacon.");
                recipe1.setIngredients(List.of(
                        "Spaghetti",
                        "Eggs",
                        "Bacon",
                        "Parmesan cheese",
                        "Black pepper"
                ));
                recipe1.setSteps(List.of(
                        "Boil the spaghetti in salted water",
                        "Fry the bacon until crispy",
                        "Mix eggs with grated Parmesan cheese",
                        "Combine pasta, bacon and egg mixture"
                ));
                recipe1.setCookingTime(20);
                recipe1.setImageUrl("https://assets.tmecosys.com/image/upload/t_web_rdp_recipe_584x480_1_5x/img/recipe/ras/Assets/61FCB2B2-DB84-4A68-ABB8-F9FBA800BCA3/Derivates/FCB48A53-86F0-4697-BE75-7B7CE9B49EBE.jpg");

                Recipe recipe2 = new Recipe();
                recipe2.setTitle("Classic Omelette");
                recipe2.setDescription("A quick and easy breakfast omelette.");
                recipe2.setIngredients(List.of(
                        "Eggs",
                        "Milk",
                        "Salt"
                ));
                recipe2.setSteps(List.of(
                        "Whisk the eggs with milk and salt",
                        "Heat a pan with oil or butter",
                        "Pour the egg mixture into the pan",
                        "Cook until set"
                ));
                recipe2.setCookingTime(10);
                recipe2.setImageUrl("https://feelgoodfoodie.net/wp-content/uploads/2021/04/how-to-make-an-omelette-10.jpg");

                recipeRepository.saveAll(List.of(recipe1, recipe2));
            }
        };
    }
}

