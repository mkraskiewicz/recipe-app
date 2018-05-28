package com.mkraskiewicz.recipeapp.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Maciej on 27/05/2018
 */
public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile file);

}
