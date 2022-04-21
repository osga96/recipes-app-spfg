package com.example.learn.recipes_app.controller;

import com.example.learn.recipes_app.model.Recipe;
import com.example.learn.recipes_app.repositories.CategoryRepository;
import com.example.learn.recipes_app.repositories.RecipeRepository;
import com.example.learn.recipes_app.repositories.UnitOfMeasureRepository;
import com.example.learn.recipes_app.services.impl.RecipeServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getView() {

        //given recipes
        when(recipeRepository.findAll()).thenReturn(recipes);
        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        //when
        String pageName = indexController.getView(model);

        //then
        assertEquals("index", pageName);
        verify(recipeRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        List<Recipe> recipesList = argumentCaptor.getValue();
        assertEquals(2, recipesList.size());
        /*model.addAttribute("theRecipes", recipeService.getRecipes());
        String pageName = "index";
        assertEquals("index", pageName);
        assertEquals(recipes, model.getAttribute("theRecipes"));*/
    }
}