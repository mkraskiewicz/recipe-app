package com.mkraskiewicz.recipeapp.controllers;

import com.mkraskiewicz.recipeapp.commands.RecipeCommand;
import com.mkraskiewicz.recipeapp.services.ImageService;
import com.mkraskiewicz.recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Maciej on 27/05/2018
 */
public class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    ImageController imageController;

    MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(imageService,recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void getImageForm() throws Exception{

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));
        verify(recipeService, times(1)).findRecipeCommandById(anyLong());
    }

    @Test
    public void handleImagePost() throws Exception{

        MockMultipartFile mockMultipartFile =
                new MockMultipartFile("imagefile","testing.txt",
                        "text/plain", "mkraskiewicz".getBytes());
        mockMvc.perform(multipart("/recipe/1/image").file(mockMultipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/recipe/1/show"));

        verify(imageService,times(1)).saveImageFile(anyLong(),any());
    }

    @Test
    public void renderImageFromDBTest() throws Exception{

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        String fakeImageDescription = "Fake image text";
        Byte[] bytesBoxed = new Byte[fakeImageDescription.getBytes().length];
        int itr = 0;

        for(byte primitiveByte : fakeImageDescription.getBytes()){
            bytesBoxed[itr++] = primitiveByte;
        }

        recipeCommand.setImage(bytesBoxed);

        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(fakeImageDescription.getBytes().length,responseBytes.length);


    }
}
