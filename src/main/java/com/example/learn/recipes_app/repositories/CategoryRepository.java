package com.example.learn.recipes_app.repositories;

import com.example.learn.recipes_app.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    public Optional<Category> findByCategoryName(String categoryName);
}
