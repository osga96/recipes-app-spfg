package com.example.learn.recipes_app.controller;

import com.example.learn.recipes_app.commands.RecipeCommand;
import com.example.learn.recipes_app.model.Recipe;
import com.example.learn.recipes_app.repositories.RecipeRepository;
import com.example.learn.recipes_app.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));
        return "recipes/show";
    }

    @GetMapping("/recipe/create/new")
    public String createNewRecipe(Model model) {

        model.addAttribute("recipe", new RecipeCommand());

        return "recipes/recipeform";
    }

    @PostMapping("/recipe/create/parameters")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand, Model model) {
        Recipe savedRecipe = recipeService.saveRecipeCommand(recipeCommand);

        if (savedRecipe == null) {
            model.addAttribute("errorMsg", "Null pointer exception");
            return "error/message";
        }

        return "recipes/created";

    }
}
