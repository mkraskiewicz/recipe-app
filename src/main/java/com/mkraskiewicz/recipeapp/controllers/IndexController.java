package com.mkraskiewicz.recipeapp.controllers;

import com.mkraskiewicz.recipeapp.model.Category;
import com.mkraskiewicz.recipeapp.model.UnitOfMeasure;
import com.mkraskiewicz.recipeapp.repositories.CategoryRepository;
import com.mkraskiewicz.recipeapp.repositories.RecipeRepository;
import com.mkraskiewicz.recipeapp.repositories.UnitOfMeasureRepository;
import com.mkraskiewicz.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Maciej on 16/04/2018
 */
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/"})
    String getIndexWebPage(Model model){

        model.addAttribute("recipies", recipeService.getAllRecipes());
        return "index";
    }
}
