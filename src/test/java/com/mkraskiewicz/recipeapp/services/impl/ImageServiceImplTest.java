package com.mkraskiewicz.recipeapp.services.impl;

import com.mkraskiewicz.recipeapp.model.Recipe;
import com.mkraskiewicz.recipeapp.repositories.RecipeRepository;
import com.mkraskiewicz.recipeapp.services.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by Maciej on 27/05/2018
 */
public class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    ImageService imageService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void saveImageFile() throws Exception {
        Long recipeID = 1l;
        MockMultipartFile mockMultipartFile =
                new MockMultipartFile("imagefile","testing.txt",
                        "text/plain", "mkraskiewicz".getBytes());

        Recipe recipe = new Recipe();
        recipe.setId(recipeID);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile(recipeID, mockMultipartFile);

        verify(recipeRepository,times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(mockMultipartFile.getBytes().length,savedRecipe.getImage().length);

    }
}
