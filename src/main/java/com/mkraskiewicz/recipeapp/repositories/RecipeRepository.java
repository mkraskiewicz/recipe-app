package com.mkraskiewicz.recipeapp.repositories;

import com.mkraskiewicz.recipeapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Maciej on 20/04/2018
 */
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
