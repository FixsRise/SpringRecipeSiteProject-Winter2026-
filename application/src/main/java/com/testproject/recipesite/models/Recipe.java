package com.testproject.recipesite.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;

    @ElementCollection
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;


    @ElementCollection
    @CollectionTable(name = "recipe_steps", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "step")
    private List<String> steps;

    private String imageUrl;

    private int cookingTime;

    @ManyToOne(optional = true)
    @JoinColumn(name = "author_id", nullable = true)
    private User author;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
