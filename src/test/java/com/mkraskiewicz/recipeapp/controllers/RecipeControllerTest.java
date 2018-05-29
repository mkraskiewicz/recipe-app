package com.mkraskiewicz.recipeapp.controllers;

import com.mkraskiewicz.recipeapp.commands.RecipeCommand;
import com.mkraskiewicz.recipeapp.exceptions.NotFoundException;
import com.mkraskiewicz.recipeapp.model.Recipe;
import com.mkraskiewicz.recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by Maciej on 11/05/2018
 */
public class RecipeControllerTest {

    static final Long RECIPE_ID = 2L;

    RecipeController recipeController;

    MockMvc mockMvc;

    @Mock
    Model model;

    @Mock
    RecipeService recipeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void getRecipeTest() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/2/show/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void getRecipeNotFoundTest() throws Exception {

        when(recipeService.findById(anyLong())).thenThrow(new NotFoundException());

        mockMvc.perform(get("/recipe/4/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));

    }

    @Test
    public void getNewRecipeFormTest() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();

        mockMvc.perform(get("/recipe/new"))
                .andExpect((status().isOk()))
                .andExpect(view().name("/recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));

    }

    @Test
    public void getRecipeNumberFormatExceptionTest() throws Exception {

        mockMvc.perform(get("/recipe/STRING/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    public void testPostNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
                .param("directions", "Some Directions")
                .param("prepTime","1")
                .param("cookTime","1")
                .param("servings", "1")

        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));
    }

    @Test
    public void PostNewRecipeFormValidationFailTest() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")

        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("/recipe/recipeform"));
    }


    @Test
    public void updateRecipeViewTest() throws Exception{

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId((RECIPE_ID));

        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/2/update"))
                .andExpect((status().isOk()))
                .andExpect(view().name("/recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void deleteRecipeTest() throws Exception{

        mockMvc.perform((get("/recipe/2/delete")))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteById(anyLong());
    }


}