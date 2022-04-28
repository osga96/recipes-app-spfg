package com.example.learn.recipes_app.services;

import com.example.learn.recipes_app.commands.RecipeCommand;
import com.example.learn.recipes_app.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RecipeService {

    List<Recipe> getRecipes();

    Recipe getRecipeById(Long id);

    Recipe saveRecipeCommand(RecipeCommand recipeCommand);

}
