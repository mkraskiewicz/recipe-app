package com.mkraskiewicz.recipeapp.services.impl;

import com.mkraskiewicz.recipeapp.commands.IngredientCommand;
import com.mkraskiewicz.recipeapp.converters.IngredientToIngredientCommand;
import com.mkraskiewicz.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.mkraskiewicz.recipeapp.model.Ingredient;
import com.mkraskiewicz.recipeapp.model.Recipe;
import com.mkraskiewicz.recipeapp.repositories.RecipeRepository;
import com.mkraskiewicz.recipeapp.services.IngredientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by Maciej on 24/05/2018
 */
public class IngredientServiceImplTest {

    IngredientService ingredientService;

    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;

    public IngredientServiceImplTest(){
        this.ingredientToIngredientCommand  = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(recipeRepository,ingredientToIngredientCommand);

    }

    @Test
    public void getByRecipeIdAndIngredientId() {

        Long firstId = 1l;
        Long secondId =2l;
        Recipe recipe = new Recipe();
        recipe.setId(firstId);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(firstId);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(secondId);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        Optional<Recipe> recipeOptional = Optional.of(recipe);


        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        IngredientCommand ingredientCommand = ingredientService.getByRecipeIdAndIngredientId(firstId,secondId);


        assertEquals(secondId, ingredientCommand.getId());
        assertEquals(firstId, ingredientCommand.getRecipeId());

        verify(recipeRepository, times(1)).findById(anyLong());

    }
}