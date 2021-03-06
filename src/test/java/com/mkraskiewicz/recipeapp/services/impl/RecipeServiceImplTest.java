package com.mkraskiewicz.recipeapp.services.impl;

import com.mkraskiewicz.recipeapp.converters.RecipeCommandToRecipe;
import com.mkraskiewicz.recipeapp.converters.RecipeToRecipeCommand;
import com.mkraskiewicz.recipeapp.exceptions.NotFoundException;
import com.mkraskiewicz.recipeapp.model.Recipe;
import com.mkraskiewicz.recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


/**
 * Created by Maciej on 07/05/2018
 */
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    public static final Long RECIPE_ID = 2L;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest(){

        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Recipe foundRecipe = recipeService.findById(RECIPE_ID);

        assertNotNull("Null object returned", foundRecipe);
        assertEquals(foundRecipe.getId(),recipe.getId());
        verify(recipeRepository, never()).findAll();
        verify(recipeRepository, times(1)).findById(anyLong());


    }

    @Test
    public void getAllRecipesTest() {

        Recipe recipe = new Recipe();
        Set<Recipe> recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeService.getAllRecipes()).thenReturn(recipeData);
        Set<Recipe> recipes = recipeService.getAllRecipes();

        assertEquals(recipes.size(),1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void deleteTest() throws Exception{

        recipeService.deleteById(RECIPE_ID);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdNotFound() throws Exception{

        Optional<Recipe> recipeOptional = Optional.empty();
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe returnedRecipe = recipeService.findById(1l);


    }
}