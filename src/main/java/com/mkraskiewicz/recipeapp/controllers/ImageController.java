package com.mkraskiewicz.recipeapp.controllers;

import com.mkraskiewicz.recipeapp.commands.RecipeCommand;
import com.mkraskiewicz.recipeapp.services.ImageService;
import com.mkraskiewicz.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Maciej on 27/05/2018
 */
@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String showUploadForm(@PathVariable String recipeId, Model model){

        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(recipeId)));
        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/{recipeId}/image")
    public String handleImagePost(@PathVariable String recipeId, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(Long.valueOf(recipeId),file);

        return "redirect:/recipe/" + recipeId + "/show";
    }

    @GetMapping("recipe/{recipeId}/recipeimage")
    public void renderImageFromDB(@PathVariable String recipeId, HttpServletResponse response) throws IOException{

        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(recipeId));
        byte[] byteArray = new byte[recipeCommand.getImage().length];
        int itr = 0;

        for(Byte wrappedByte : recipeCommand.getImage()){
            byteArray[itr++] = wrappedByte;
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    }
}
