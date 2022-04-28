package com.example.learn.recipes_app.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    @Enumerated(value = EnumType.STRING) // SI SE DEJA ORDINAL
    // (POR DEFECTO) EN LA BBDD SE MOSTRARA COMO 1,2,3
    // EN VEZ DE EASY, MODERATE, HARD
    private Difficulty difficulty;
    @Lob
    private byte[] image;
    @OneToOne(cascade = CascadeType.ALL)
    private Note note;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "recipe")
    private List<Ingredient> ingredients;
    @ManyToMany
    @JoinTable(name = "recipes_categories",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public Recipe() {
    }

    @Builder
    public Recipe(String description, Integer prepTime, Integer cookTime, Integer servings,
                  String source, String url, String directions, Difficulty difficulty,
                  byte[] image, Note note, List<Ingredient> ingredients, List<Category> categories) {
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.directions = directions;
        this.difficulty = difficulty;
        this.image = image;
        this.note = note;
        this.ingredients = ingredients;
        this.categories = categories;
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        if (this.ingredients != null) {
            this.ingredients.add(ingredient);
        } else {
            this.ingredients = new ArrayList<>();
            this.ingredients.add(ingredient);
        }
        return this;
    }

}
