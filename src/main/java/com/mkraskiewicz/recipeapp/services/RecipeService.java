package com.mkraskiewicz.recipeapp.services;

import com.mkraskiewicz.recipeapp.commands.RecipeCommand;
import com.mkraskiewicz.recipeapp.model.Recipe;

import java.util.Set;

/**
 * Created by Maciej on 23/04/2018
 */
public interface RecipeService {

    Set<Recipe> getAllRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findRecipeCommandById(Long id);
}
