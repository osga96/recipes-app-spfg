package com.example.learn.recipes_app.commands;

import com.example.learn.recipes_app.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    private String description;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    private Integer cookTime;
    private Integer servings;
    private String source;

    @URL
    private String url;

    private String directions;
    private byte[] image;
    private List<IngredientCommand> ingredients = new ArrayList<>();
    private Difficulty difficulty;
    private NotesCommand note;
    private List<CategoryCommand> categories = new ArrayList<>();
}
