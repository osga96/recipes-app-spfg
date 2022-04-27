package com.example.learn.recipes_app.repositories;

import com.example.learn.recipes_app.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Override
    Optional<Recipe> findById(Long aLong);
}
