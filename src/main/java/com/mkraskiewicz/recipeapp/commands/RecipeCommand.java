package com.mkraskiewicz.recipeapp.commands;

import com.mkraskiewicz.recipeapp.enums.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 3, max = 256)
    private String description;

    @Min(1)
    @Max(999)
    private int cookTime;

    @Min(1)
    @Max(999)
    private int prepTime;

    @Min(1)
    @Max(100)
    private int servings;
    private String source;

    @URL
    private String url;

    private String directions;
    private NotesCommand notes;
    private Difficulty difficulty;
    private Set<CategoryCommand> categories = new HashSet<>();
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
}
