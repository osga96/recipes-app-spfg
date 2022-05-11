package com.example.learn.recipes_app.services;

import com.example.learn.recipes_app.model.Category;
import java.util.List;

public interface CategoryService {

    List<Category> getCategories();
    Category getCategoryById(Long id);

}
