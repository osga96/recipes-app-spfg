package com.example.learn.recipes_app.controller;

import com.example.learn.recipes_app.commands.IngredientCommand;
import com.example.learn.recipes_app.model.Ingredient;
import com.example.learn.recipes_app.services.IngredientService;
import com.example.learn.recipes_app.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IngredientController {

    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;


    public IngredientController(IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/ingredient/{ingredientId}/delete")
    public String getIngredientDetails(@PathVariable String ingredientId) {
        ingredientService.deleteIngredient(Long.valueOf(ingredientId));
        return "ingredients/deleted";
    }

    @PostMapping("/ingredient/create")
    public String createOrUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
        Ingredient ingredient = ingredientService.saveIngredientCommand(ingredientCommand);
        return "ingredients/created";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String createOrUpdateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {

        Optional<Ingredient> ingredientOptional = ingredientService.getIngredientByIngredientId(Long.valueOf(ingredientId));

        if (ingredientOptional.isPresent()) {

            model.addAttribute("ingredient", ingredientOptional.get());
            model.addAttribute("unitOfMeasureList", unitOfMeasureService.getAllUnitsOfMeasure());

        } else {

            return "/error/message";

        }
        return "ingredients/ingredientform";
    }

}
