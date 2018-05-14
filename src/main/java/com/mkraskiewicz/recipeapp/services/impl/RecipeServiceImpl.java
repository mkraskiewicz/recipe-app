package com.mkraskiewicz.recipeapp.services.impl;

import com.mkraskiewicz.recipeapp.model.Recipe;
import com.mkraskiewicz.recipeapp.repositories.RecipeRepository;
import com.mkraskiewicz.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        log.debug("In Recipe Service.");
        Set<Recipe> recipeSet = new HashSet();
        recipeRepository.findAll().forEach((r) -> recipeSet.add(r));
        return recipeSet;
    }

    @Override
    public Recipe findById(long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found.");
        }

        return recipeOptional.get();
    }
}
