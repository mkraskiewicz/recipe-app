package com.mkraskiewicz.recipeapp.services.impl;

import com.mkraskiewicz.recipeapp.model.Recipe;
import com.mkraskiewicz.recipeapp.repositories.RecipeRepository;
import com.mkraskiewicz.recipeapp.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maciej on 23/04/2018
 */
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        Set<Recipe> recipeSet = new HashSet();
        recipeRepository.findAll().forEach((r) -> recipeSet.add(r));
        return recipeSet;
    }
}
