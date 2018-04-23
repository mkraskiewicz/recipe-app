package com.mkraskiewicz.recipeapp.repositories;

import com.mkraskiewicz.recipeapp.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Maciej on 20/04/2018
 */
@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {

    Optional<UnitOfMeasure> findUnitOfMeasureByDescription(String description);
}
