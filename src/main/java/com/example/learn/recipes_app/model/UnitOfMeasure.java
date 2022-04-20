package com.example.learn.recipes_app.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String unitOfMeasure;
    @OneToOne
    private Ingredient ingredient;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UnitOfMeasure)) return false;
        final UnitOfMeasure other = (UnitOfMeasure) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$unitOfMeasure = this.getUnitOfMeasure();
        final Object other$unitOfMeasure = other.getUnitOfMeasure();
        if (this$unitOfMeasure == null ? other$unitOfMeasure != null : !this$unitOfMeasure.equals(other$unitOfMeasure))
            return false;
        final Object this$ingredient = this.getIngredient();
        final Object other$ingredient = other.getIngredient();
        if (this$ingredient == null ? other$ingredient != null : !this$ingredient.equals(other$ingredient))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UnitOfMeasure;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $unitOfMeasure = this.getUnitOfMeasure();
        result = result * PRIME + ($unitOfMeasure == null ? 43 : $unitOfMeasure.hashCode());
        final Object $ingredient = this.getIngredient();
        result = result * PRIME + ($ingredient == null ? 43 : $ingredient.hashCode());
        return result;
    }

    public String toString() {
        return "UnitOfMeasure(id=" + this.getId() + ", unitOfMeasure=" + this.getUnitOfMeasure() + ")";
    }

}
