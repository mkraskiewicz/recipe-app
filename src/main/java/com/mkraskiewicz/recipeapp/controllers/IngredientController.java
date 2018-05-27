package com.mkraskiewicz.recipeapp.controllers;

import com.mkraskiewicz.recipeapp.commands.IngredientCommand;
import com.mkraskiewicz.recipeapp.commands.RecipeCommand;
import com.mkraskiewicz.recipeapp.commands.UnitOfMeasureCommand;
import com.mkraskiewicz.recipeapp.services.IngredientService;
import com.mkraskiewicz.recipeapp.services.RecipeService;
import com.mkraskiewicz.recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Maciej on 22/05/2018
 */
@Controller
@Slf4j
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String showIngredients(@PathVariable Long recipeId, Model model){
        log.debug("In the Ingredient Controller");

        model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));

        return "/recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredientById(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        log.debug("Showing Ingredient");

        model.addAttribute("ingredient", ingredientService.getByRecipeIdAndIngredientId(
                Long.valueOf(recipeId),Long.valueOf(ingredientId)));

        return "/recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable String recipeId, Model model){

        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(recipeId));
    //TODO raise exception if null
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "/recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        log.debug("Updating Ingredient");

        model.addAttribute("ingredient", ingredientService.getByRecipeIdAndIngredientId(
                Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "/recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){

        log.debug("Deleting ingredient:" + ingredientId);
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand ingredientCommand = ingredientService.saveIngredientCommand(command);

        log.debug("Saved recipe ID:" + ingredientCommand.getRecipeId());
        log.debug("Saved ingredient ID:" + ingredientCommand.getId());

        return "redirect:/recipe/" + ingredientCommand.getRecipeId()
                + "/ingredient/" + ingredientCommand.getId() + "/show";
    }
}
