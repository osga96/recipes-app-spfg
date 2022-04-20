package com.example.learn.recipes_app.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNotes;

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Note)) return false;
        final Note other = (Note) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$recipe = this.getRecipe();
        final Object other$recipe = other.getRecipe();
        if (this$recipe == null ? other$recipe != null : !this$recipe.equals(other$recipe)) return false;
        final Object this$recipeNotes = this.getRecipeNotes();
        final Object other$recipeNotes = other.getRecipeNotes();
        if (this$recipeNotes == null ? other$recipeNotes != null : !this$recipeNotes.equals(other$recipeNotes))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Note;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $recipe = this.getRecipe();
        result = result * PRIME + ($recipe == null ? 43 : $recipe.hashCode());
        final Object $recipeNotes = this.getRecipeNotes();
        result = result * PRIME + ($recipeNotes == null ? 43 : $recipeNotes.hashCode());
        return result;
    }

    public String toString() {
        return "Note(id=" + this.getId() + ", recipeNotes=" + this.getRecipeNotes() + ")";
    }

}
