package com.mkraskiewicz.recipeapp.services.impl;

import com.mkraskiewicz.recipeapp.model.Recipe;
import com.mkraskiewicz.recipeapp.repositories.RecipeRepository;
import com.mkraskiewicz.recipeapp.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Maciej on 27/05/2018
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {

        try{
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];
            int itr = 0;

            for(byte b : file.getBytes()){
                byteObjects[itr++] = b;
            }
            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        }catch (IOException exception){
            log.error("Error occured", exception);
            exception.printStackTrace();
        }
    }
}
