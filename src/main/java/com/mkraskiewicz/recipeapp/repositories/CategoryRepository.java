package com.mkraskiewicz.recipeapp.repositories;

import com.mkraskiewicz.recipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Maciej on 20/04/2018
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findCategoryByDescription(String description);
}
