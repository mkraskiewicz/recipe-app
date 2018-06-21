package com.mkraskiewicz.recipeapp.repositories;

import com.mkraskiewicz.recipeapp.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maciej on 07/05/2018
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() throws Exception {

        Iterable<UnitOfMeasure> a = unitOfMeasureRepository.findAll();
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon");

        assertEquals("Teaspoon", uomOptional.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() throws Exception {

        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findUnitOfMeasureByDescription("Cup");

        assertEquals("Cup", uomOptional.get().getDescription());
    }

}