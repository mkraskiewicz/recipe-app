package com.mkraskiewicz.recipeapp.controllers;

import com.mkraskiewicz.recipeapp.services.IngredientService;
import com.mkraskiewicz.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Maciej on 22/05/2018
 */
@Controller
@Slf4j
public class IngredientController {

    RecipeService recipeService;
    IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String showIngredients(@PathVariable Long recipeId, Model model){
        log.debug("In the Ingredient Controller");

        model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));

        return "/recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredientById(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        log.debug("Showing Ingredient");

        model.addAttribute("ingredient", ingredientService.getByRecipeIdAndIngredientId(
                Long.valueOf(recipeId),Long.valueOf(ingredientId)));

        return "/recipe/ingredient/show";
    }
}
