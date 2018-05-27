package com.mkraskiewicz.recipeapp.services.impl;

import com.mkraskiewicz.recipeapp.commands.IngredientCommand;
import com.mkraskiewicz.recipeapp.converters.IngredientCommandToIngredient;
import com.mkraskiewicz.recipeapp.converters.IngredientToIngredientCommand;
import com.mkraskiewicz.recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.mkraskiewicz.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.mkraskiewicz.recipeapp.model.Ingredient;
import com.mkraskiewicz.recipeapp.model.Recipe;
import com.mkraskiewicz.recipeapp.repositories.RecipeRepository;
import com.mkraskiewicz.recipeapp.repositories.UnitOfMeasureRepository;
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
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final Long FIRST_ID = 1l;
    private final Long SECOND_ID =2l;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImplTest(){
        this.ingredientToIngredientCommand  = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(recipeRepository, unitOfMeasureRepository,
                 ingredientToIngredientCommand, ingredientCommandToIngredient);

    }

    @Test
    public void getByRecipeIdAndIngredientId() {


        Recipe recipe = new Recipe();
        recipe.setId(FIRST_ID);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(FIRST_ID);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(SECOND_ID);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        Optional<Recipe> recipeOptional = Optional.of(recipe);


        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        IngredientCommand ingredientCommand = ingredientService.getByRecipeIdAndIngredientId(FIRST_ID, SECOND_ID);


        assertEquals(SECOND_ID, ingredientCommand.getId());
        assertEquals(FIRST_ID, ingredientCommand.getRecipeId());

        verify(recipeRepository, times(1)).findById(anyLong());

    }

    @Test
    public void saveIngredientCommand() throws Exception{


        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(FIRST_ID);
        ingredientCommand.setRecipeId(SECOND_ID);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(FIRST_ID);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        assertEquals(savedCommand.getId(), ingredientCommand.getId());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any(Recipe.class));

    }

    @Test
    public void deleteByIdTest() throws Exception{

        Recipe recipe = new Recipe();
        recipe.setId(1l);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1l);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        ingredientService.deleteById(1l, 1l);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}