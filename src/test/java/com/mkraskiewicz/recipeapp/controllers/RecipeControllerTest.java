package com.mkraskiewicz.recipeapp.controllers;

import com.mkraskiewicz.recipeapp.model.Recipe;
import com.mkraskiewicz.recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by Maciej on 11/05/2018
 */
public class RecipeControllerTest {

    RecipeController recipeController;

    @Mock
    Model model;

    @Mock
    RecipeService recipeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
    }

    @Test
    public void getRecipeTest() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1l);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

}