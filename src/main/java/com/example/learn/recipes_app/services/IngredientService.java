package com.example.learn.recipes_app.services;

import com.example.learn.recipes_app.commands.IngredientCommand;
import com.example.learn.recipes_app.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    public List<Ingredient> getIngredients();

    public void deleteIngredient(Long ingredientId);

    public Optional<Ingredient> getIngredientByIngredientId(Long ingredientId);

    public Ingredient saveIngredientCommand(IngredientCommand ingredientCommand);
}
