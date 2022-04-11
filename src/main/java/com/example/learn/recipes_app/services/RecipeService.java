package com.example.learn.recipes_app.services;

import com.example.learn.recipes_app.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RecipeService {

    List<Recipe> getRecipes();

}
