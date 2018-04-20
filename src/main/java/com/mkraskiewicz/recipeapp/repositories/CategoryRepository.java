package com.mkraskiewicz.recipeapp.repositories;

import com.mkraskiewicz.recipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Maciej on 20/04/2018
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
