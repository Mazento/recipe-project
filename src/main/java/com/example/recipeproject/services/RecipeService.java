package com.example.recipeproject.services;

import com.example.recipeproject.domain.Recipe;

import java.util.Set;


public interface RecipeService {

    Set<Recipe> getRecipes();
}
