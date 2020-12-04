package com.example.recipeproject.bootstrap;

import com.example.recipeproject.domain.*;
import com.example.recipeproject.repositories.CategoryRepository;
import com.example.recipeproject.repositories.RecipeRepository;
import com.example.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes =  new ArrayList<>();

        // ugly code, but it's not worth it making it better in the context of this app
        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (teaSpoonUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UON Not found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (tableSpoonUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UON Not found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (cupUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UON Not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (dashUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UON Not found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (pintUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UON Not found");
        }

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (eachUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UON Not found");
        }

        // get Optional UOMs
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure eachUom = eachUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (americanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected Category Not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (mexicanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected Category Not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        // Yummy Guacamole
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(5);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Here should be very long recipe of the Perfect Guacamole...");

        // Set Notes
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Perfect Guacamole notes...");
        guacRecipe.setNotes(guacNotes);

        // Set Ingredients
        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        // Set Categories
        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        // Add recipe to the result list
        recipes.add(guacRecipe);

        return recipes;
    }
}
