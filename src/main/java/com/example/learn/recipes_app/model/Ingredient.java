package com.example.learn.recipes_app.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String description;
    private BigDecimal amount;
    @ManyToOne
    private Recipe recipe;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, Recipe recipe, UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.recipe = recipe;
        this.unitOfMeasure = unitOfMeasure;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Ingredient)) return false;
        final Ingredient other = (Ingredient) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final Object this$recipe = this.getRecipe();
        final Object other$recipe = other.getRecipe();
        if (this$recipe == null ? other$recipe != null : !this$recipe.equals(other$recipe)) return false;
        final Object this$unitOfMeasure = this.getUnitOfMeasure();
        final Object other$unitOfMeasure = other.getUnitOfMeasure();
        if (this$unitOfMeasure == null ? other$unitOfMeasure != null : !this$unitOfMeasure.equals(other$unitOfMeasure))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Ingredient;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final Object $recipe = this.getRecipe();
        result = result * PRIME + ($recipe == null ? 43 : $recipe.hashCode());
        final Object $unitOfMeasure = this.getUnitOfMeasure();
        result = result * PRIME + ($unitOfMeasure == null ? 43 : $unitOfMeasure.hashCode());
        return result;
    }

    public String toString() {
        return "Ingredient(id=" + this.getId() + ", description=" + this.getDescription() + ", amount=" + this.getAmount() + ", unitOfMeasure=" + this.getUnitOfMeasure() + ")";
    }

}
