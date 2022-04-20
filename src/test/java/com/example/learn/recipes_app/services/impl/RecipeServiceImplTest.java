package com.example.learn.recipes_app.services.impl;

import com.example.learn.recipes_app.model.Recipe;
import com.example.learn.recipes_app.repositories.RecipeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;
    AutoCloseable autoCloseable;
    List<Recipe> recipes;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        recipes = Arrays.asList(recipe1, recipe2);
    }

    @AfterEach
    void afterEach() {
        try {
            autoCloseable.close();
        } catch (Exception e) {
            autoCloseable = null;
        }
    }

    @Test
    void getRecipes() {
        when(recipeRepository.findAll()).thenReturn(recipes);
        List<Recipe> recipes = recipeService.getRecipes();
        assertEquals(2, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }
}