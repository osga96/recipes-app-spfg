package com.example.learn.recipes_app.controller;

import com.example.learn.recipes_app.model.Recipe;
import com.example.learn.recipes_app.repositories.CategoryRepository;
import com.example.learn.recipes_app.repositories.RecipeRepository;
import com.example.learn.recipes_app.repositories.UnitOfMeasureRepository;
import com.example.learn.recipes_app.services.impl.RecipeServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Mock
    CategoryRepository categoryRepository;
    AutoCloseable autoCloseable;
    List<Recipe> recipes;
    @Mock
    Model model;
    IndexController indexController;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        recipes = Arrays.asList(recipe1, recipe2);
        indexController = new IndexController(unitOfMeasureRepository, categoryRepository, recipeService);
    }

    @AfterEach
    void cleanUp() {
        try {
            autoCloseable.close();
        } catch (Exception e) {
            autoCloseable = null;
        }
    }

    @Test
    void getView() {

        String pageName = indexController.getView(model);

        assertEquals("index", pageName);
        verify(recipeRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("recipes"), anyList());

        /*when(recipeRepository.findAll()).thenReturn(recipes);
        model.addAttribute("theRecipes", recipeService.getRecipes());
        String pageName = "index";
        assertEquals("index", pageName);
        assertEquals(recipes, model.getAttribute("theRecipes"));*/
    }
}