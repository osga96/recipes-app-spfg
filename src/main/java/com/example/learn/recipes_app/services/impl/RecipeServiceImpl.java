package com.example.learn.recipes_app.services.impl;

import com.example.learn.recipes_app.commands.RecipeCommand;
import com.example.learn.recipes_app.converters.RecipeCommandToRecipe;
import com.example.learn.recipes_app.model.Recipe;
import com.example.learn.recipes_app.repositories.RecipeRepository;
import com.example.learn.recipes_app.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public List<Recipe> getRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public Recipe saveRecipeCommand(RecipeCommand recipeCommand) {
        try {

            return recipeRepository.save(recipeCommandToRecipe.convert(recipeCommand));

        } catch (NullPointerException nullPointerException) {

            return null;

        }
    }

}
