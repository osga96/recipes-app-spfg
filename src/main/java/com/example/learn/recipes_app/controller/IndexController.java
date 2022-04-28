package com.example.learn.recipes_app.controller;

import com.example.learn.recipes_app.repositories.CategoryRepository;
import com.example.learn.recipes_app.repositories.UnitOfMeasureRepository;
import com.example.learn.recipes_app.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeService recipeService;

    public IndexController(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeService recipeService) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getView(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }

    @RequestMapping("/uom")
    public String getTestViewUnitOfMeasure(Model model) {
        model.addAttribute("unitOfMeasureCup", unitOfMeasureRepository.findByUnitOfMeasure("Cup"));
        return "test/uom";
    }

    @RequestMapping("/category")
    public String getTestViewCategory(Model model) {
        model.addAttribute("categorySpanish", categoryRepository.findByCategoryName("Spanish"));
        return "test/category";
    }

    // este sirve como /recipe?id=id
    @RequestMapping("/recipe")
    public String getRecipeById(Long id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        return "test/recipe";
    }

    // este sirve como /recipe/id
    @RequestMapping("/recipe/{id}")
    public String getRecipeByIdUrl(@PathVariable String id, Model model) {

        try {

            model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));

        } catch (NumberFormatException nfe) {

            model.addAttribute("errorMsg", nfe);
            return "error/message";
        }

        return "test/recipe";
    }

    // este sirve como /recipe/id
    @RequestMapping("/categories")
    public String getCategories(Model model) {

        model.addAttribute("categoriesList", categoryRepository.findAll());

        return "categories/list";
    }

}
