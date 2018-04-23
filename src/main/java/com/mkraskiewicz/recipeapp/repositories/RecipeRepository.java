package com.mkraskiewicz.recipeapp.repositories;

import com.mkraskiewicz.recipeapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Maciej on 20/04/2018
 */
@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
