package com.example.learn.recipes_app.services.impl;

import com.example.learn.recipes_app.commands.IngredientCommand;
import com.example.learn.recipes_app.converters.IngredientCommandToIngredient;
import com.example.learn.recipes_app.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.example.learn.recipes_app.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.learn.recipes_app.model.Ingredient;
import com.example.learn.recipes_app.model.UnitOfMeasure;
import com.example.learn.recipes_app.repositories.IngredientRepository;
import com.example.learn.recipes_app.services.IngredientService;
import com.example.learn.recipes_app.services.UnitOfMeasureService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientCommandToIngredient ingredientCommandToIngredient, UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand, UnitOfMeasureService unitOfMeasureService) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @Override
    public List<Ingredient> getIngredients() {
        /*return StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());*/

        List<Ingredient> ingredients = new ArrayList<>();

        ingredientRepository.findAll().forEach(ingredients :: add);

        return ingredients;
    }

    @Override
    public void deleteIngredient(Long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }

    @Override
    public Optional<Ingredient> getIngredientByIngredientId(Long ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    @Override
    public Ingredient saveIngredientCommand(IngredientCommand ingredientCommand) {
        try {

            Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureService.getUnitOfMeasureById(ingredientCommand.getUnitOfMeasure().getId());

            if (unitOfMeasure.isPresent()) {
                unitOfMeasureService.saveUnitOfMeasure(unitOfMeasure.get());
                ingredientCommand.setUnitOfMeasure(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure.get()));
            }

            return ingredientRepository.save(Objects.requireNonNull(ingredientCommandToIngredient.convert(ingredientCommand)));

        } catch (NullPointerException nullPointerException) {
            throw new RuntimeException("Ingredient was null");
        }
    }
}
