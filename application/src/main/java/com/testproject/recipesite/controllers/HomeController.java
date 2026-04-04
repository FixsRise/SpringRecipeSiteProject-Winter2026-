package com.testproject.recipesite.controllers;

import com.testproject.recipesite.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private RecipeRepository recipeRepository;

    public HomeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("recipeList", recipeRepository.findAll());
        return "index";
    }




}
