package com.testproject.recipesite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
public class RecipesiteApplication {

    public static void main(String[] args) {


        SpringApplication.run(RecipesiteApplication.class, args);
    }

}
