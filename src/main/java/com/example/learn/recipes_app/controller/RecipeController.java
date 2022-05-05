package com.example.learn.recipes_app.controller;

import com.example.learn.recipes_app.commands.RecipeCommand;
import com.example.learn.recipes_app.exceptions.NotFoundException;
import com.example.learn.recipes_app.model.Ingredient;
import com.example.learn.recipes_app.model.Recipe;
import com.example.learn.recipes_app.repositories.RecipeRepository;
import com.example.learn.recipes_app.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){

        // IMPORTANT: Se puede hacer de las dos maneras, desde el servicio y desde el controlador,
        // en el curso se hace desde el servicio, con lo que lo comento aquí

        /*if (recipeService.getRecipeById(Long.valueOf(id)) == null) {
            throw new NotFoundException("The recipe with the specified ID was not found");
        }*/

        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));
        return "recipes/show";
    }

    @GetMapping("/recipe/create/new")
    public String createNewRecipe(Model model) {

        model.addAttribute("recipe", new RecipeCommand());

        return "recipes/recipeform";
    }

    @PostMapping("/recipe/create/parameters")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand, Model model) {
        Recipe savedRecipe = recipeService.saveRecipeCommand(recipeCommand);

        if (savedRecipe == null) {
            model.addAttribute("errorMsg", "Null pointer exception");
            return "error/message";
        }
        model.addAttribute("recipe", savedRecipe);

        return "recipes/created";

    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));
        return "recipes/recipeform";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id, Model model) {
        recipeService.deleteRecipe(Long.valueOf(id));
        return "recipes/deleted";
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        Recipe recipe = recipeService.getRecipeById(Long.valueOf(recipeId));

        if (recipe == null || recipe.getIngredients().isEmpty()) {
            return "/error/message";
        }

        model.addAttribute("recipe", recipe);

        return "ingredients/list";
    }

    @GetMapping("/recipe/image/{recipeId}")
    public void showProductImage(@PathVariable String recipeId, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        Recipe recipe = recipeService.getRecipeById(Long.valueOf(recipeId));

        InputStream is = new ByteArrayInputStream(recipe.getImage());
        IOUtils.copy(is, response.getOutputStream());
    }

    @GetMapping("/recipe/{recipeId}/update/image/form")
    public String updateImageForm(@PathVariable String recipeId, Model model) {

        Recipe recipe = recipeService.getRecipeById(Long.valueOf(recipeId));
        model.addAttribute("recipe", recipe);

        return "recipes/imageform";
    }

    @PostMapping("/recipe/update/image/{recipeId}")
    public String updateImage(@PathVariable String recipeId, @RequestParam("imagefile") MultipartFile file){

        try {
            Recipe recipe = recipeService.getRecipeById(Long.valueOf(recipeId));
            recipe.setImage(file.getBytes());

            recipeService.saveRecipe(recipe);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/recipes/updated";
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("The recipe was not found, returning an error page.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
