package com.mkraskiewicz.recipeapp.converters;


import com.mkraskiewicz.recipeapp.commands.CategoryCommand;
import com.mkraskiewicz.recipeapp.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Maciej on 14/05/2018
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand,Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source){
        if(source == null){
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
