package com.testproject.recipesite.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String email;
    private String password;

}