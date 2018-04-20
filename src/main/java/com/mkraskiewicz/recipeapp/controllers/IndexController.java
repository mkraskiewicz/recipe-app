package com.mkraskiewicz.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Maciej on 16/04/2018
 */
@Controller
public class IndexController {

    @RequestMapping({"","/"})
    String getIndexWebPage(){
        System.out.println("TEST");
        return "index";
    }
}
