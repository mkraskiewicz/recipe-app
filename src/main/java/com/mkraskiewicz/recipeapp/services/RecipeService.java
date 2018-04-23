package com.mkraskiewicz.recipeapp.services;

import com.mkraskiewicz.recipeapp.model.Recipe;

import java.util.Set;

/**
 * Created by Maciej on 23/04/2018
 */
public interface RecipeService {

    Set<Recipe> getAllRecipes();
}
