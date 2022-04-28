package com.example.learn.recipes_app.commands;

import com.example.learn.recipes_app.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 6/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private List<IngredientCommand> ingredients = new ArrayList<>();
    private Difficulty difficulty;
    private NotesCommand note;
    private List<CategoryCommand> categories = new ArrayList<>();
}
