package com.example.learn.recipes_app.controller;

import com.example.learn.recipes_app.repositories.CategoryRepository;
import com.example.learn.recipes_app.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;

    public IndexController(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping({"","/","/index"})
    public String getView() {
        return "index";
    }

    @RequestMapping("/uom")
    public String getTestViewUnitOfMeasure(Model model) {
        model.addAttribute("unitOfMeasureCup", unitOfMeasureRepository.findByUnitOfMeasure("Cup"));
        return "test/uom";
    }

    @RequestMapping("/category")
    public String getTestViewCategory(Model model) {
        model.addAttribute("categorySpanish", categoryRepository.findByCategoryName("Spanish"));
        return "test/category";
    }

}
