package com.mkraskiewicz.recipeapp.commands;

import com.mkraskiewicz.recipeapp.enums.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maciej on 14/05/2018
 */
@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private int cookTime;
    private int prepTime;
    private int servings;
    private String source;
    private String url;
    private String directions;
    private NotesCommand notes;
    private Difficulty difficulty;
    private Set<CategoryCommand> categories = new HashSet<>();
    private Set<IngredientCommand> ingredients = new HashSet<>();
}
