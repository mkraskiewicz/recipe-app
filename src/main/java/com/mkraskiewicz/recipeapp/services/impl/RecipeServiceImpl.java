package com.mkraskiewicz.recipeapp.services.impl;

import com.mkraskiewicz.recipeapp.commands.RecipeCommand;
import com.mkraskiewicz.recipeapp.converters.RecipeCommandToRecipe;
import com.mkraskiewicz.recipeapp.converters.RecipeToRecipeCommand;
import com.mkraskiewicz.recipeapp.model.Recipe;
import com.mkraskiewicz.recipeapp.repositories.RecipeRepository;
import com.mkraskiewicz.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Maciej on 23/04/2018
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        log.debug("In Recipe Service.");
        Set<Recipe> recipeSet = new HashSet();
        recipeRepository.findAll().forEach((r) -> recipeSet.add(r));
        return recipeSet;
    }

    @Override
    @Transactional
    public Recipe findById(long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found.");
        }

        return recipeOptional.get();
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved Recipe " + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
