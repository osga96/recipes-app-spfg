package com.example.learn.recipes_app.services.impl;

import com.example.learn.recipes_app.commands.RecipeCommand;
import com.example.learn.recipes_app.converters.RecipeCommandToRecipe;
import com.example.learn.recipes_app.exceptions.NotFoundException;
import com.example.learn.recipes_app.model.Recipe;
import com.example.learn.recipes_app.repositories.RecipeRepository;
import com.example.learn.recipes_app.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isEmpty()) {
            throw new NotFoundException("The recipe with the specified ID was not found for the specified id: " + id);
        }

        return recipeOptional.get();
    }

    @Override
    public Recipe saveRecipeCommand(RecipeCommand recipeCommand) {
        try {

            return recipeRepository.save(recipeCommandToRecipe.convert(recipeCommand));

        } catch (NullPointerException nullPointerException) {

            return null;

        }
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        try {

            return recipeRepository.save(recipe);

        } catch (NullPointerException nullPointerException) {

            return null;

        }
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

}
