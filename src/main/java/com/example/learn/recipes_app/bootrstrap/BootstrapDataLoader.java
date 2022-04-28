package com.example.learn.recipes_app.bootrstrap;

import com.example.learn.recipes_app.model.*;
import com.example.learn.recipes_app.repositories.CategoryRepository;
import com.example.learn.recipes_app.repositories.IngredientRepository;
import com.example.learn.recipes_app.repositories.RecipeRepository;
import com.example.learn.recipes_app.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class BootstrapDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;
    private IngredientRepository ingredientRepository;

    public BootstrapDataLoader(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            bootstrapRecipesData();
        } catch (IOException e) {
            log.error("Errorrr");
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void bootstrapRecipesData() throws IOException {

        Recipe guacamole = new Recipe();

        guacamole.setDescription("The word \"guacamole\" and the dip, are both originally from Mexico, where avocados have been cultivated for thousands of years. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "\n");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(3);
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/#toc-ingredients-for-easy-guacamole");
        guacamole.setDirections("Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "How to make guacamole - scoring avocado\n" +
                "Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "How to make guacamole - smashing avocado with fork\n" +
                "Add remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.");
        guacamole.setDifficulty(Difficulty.EASY);

        Resource resource = new ClassPathResource("static/images/guacamole.png");
        byte[] guacamoleImage = resource.getInputStream().readAllBytes();
        guacamole.setImage(guacamoleImage);

        Note guacamoleNotes = new Note();
        guacamoleNotes.setRecipe(guacamole);
        guacamoleNotes.setRecipeNotes("Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.");

        guacamole.setNote(guacamoleNotes);

        UnitOfMeasure piece = new UnitOfMeasure();
        piece.setUnitOfMeasure("piece");

        unitOfMeasureRepository.save(piece);

        Ingredient avocados = new Ingredient("Avocado", new BigDecimal(2), guacamole, piece);
        piece.setIngredient(avocados);

        guacamole.addIngredient(avocados);

        Category categoryAvocado = new Category();
        categoryAvocado.setCategoryName("IDK");
        categoryRepository.save(categoryAvocado);

        Category categoryBreaded = new Category();
        categoryAvocado.setCategoryName("Breaded");
        categoryRepository.save(categoryBreaded);

        Optional<Category> category = categoryRepository.findByCategoryName("Fast food");
        Optional<Category> category2 = categoryRepository.findByCategoryName("Breaded");

        if (category.isPresent()) {

            guacamole.setCategories(Arrays.asList(categoryAvocado, category.get()));

        } else {
            guacamole.setCategories(List.of(categoryAvocado));
        }


        recipeRepository.save(guacamole);
        ingredientRepository.save(avocados);

        log.info(guacamole.toString());

        Recipe recipe2 = Recipe.builder()
                .description("Croquetas!")
                .prepTime(12)
                .cookTime(60)
                .servings(20)
                .source("Personal experience")
                .url("none")
                .directions("lorem ipsum")
                .difficulty(Difficulty.MODERATE)
                .categories(Arrays.asList(category.orElse(null), category2.orElse(null))).build();

        recipeRepository.save(recipe2);

    }
}
