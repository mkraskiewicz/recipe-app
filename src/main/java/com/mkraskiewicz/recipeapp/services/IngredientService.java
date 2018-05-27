package com.mkraskiewicz.recipeapp.services;

import com.mkraskiewicz.recipeapp.commands.IngredientCommand;

/**
 * Created by Maciej on 24/05/2018
 */
public interface IngredientService {

    IngredientCommand getByRecipeIdAndIngredientId(Long recipeId, Long id);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteById(Long recipeId, Long ingredientId);
}
