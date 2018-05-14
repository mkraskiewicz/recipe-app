package com.mkraskiewicz.recipeapp.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maciej on 07/05/2018
 */
public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getIdTest() {
        Long idValue = 2l;

        category.setId(idValue);

        assertEquals(idValue,category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}