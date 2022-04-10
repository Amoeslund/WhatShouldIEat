package com.amoeslund.whatshouldieat.services;

import com.amoeslund.whatshouldieat.helpers.StringExtensions;
import com.amoeslund.whatshouldieat.repositories.entities.RecipeTag;
import com.amoeslund.whatshouldieat.repositories.RecipeRepository;
import com.amoeslund.whatshouldieat.repositories.entities.Recipe;
import com.amoeslund.whatshouldieat.repositories.RecipeStatRepository;
import com.amoeslund.whatshouldieat.repositories.RecipeTagRepository;
import com.amoeslund.whatshouldieat.webcrawler.ValdemarsroCrawlerImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final ValdemarsroCrawlerImpl valdemarsroCrawler;
    private final RecipeRepository recipeRepository;
    private final RecipeStatRepository recipeStatRepository;
    private final RecipeTagRepository recipeTagRepository;

    public RecipeService(ValdemarsroCrawlerImpl valdemarsroCrawler, RecipeRepository recipeRepository, RecipeStatRepository recipeStatRepository, RecipeTagRepository recipeTagRepository) {
        this.valdemarsroCrawler = valdemarsroCrawler;
        this.recipeRepository = recipeRepository;
        this.recipeStatRepository = recipeStatRepository;
        this.recipeTagRepository = recipeTagRepository;
    }

    public List<Recipe> updateRecipes() {
        List<Recipe> recipes = valdemarsroCrawler.getPageRecipes();
        return saveRecipes(recipes);
    }

    private List<Recipe> saveRecipes(List<Recipe> recipes) {
        recipeStatRepository.saveAll(recipes.stream().flatMap(x -> x.getRecipeStats().stream().distinct()).toList());
        recipeTagRepository.saveAll(recipes.stream().flatMap(x -> x.getRecipeTags().stream().distinct()).toList());
        return recipeRepository.saveAllAndFlush(recipes);
    }

    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipe(long id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> getRecipesByTags(List<String> tags) {
        return recipeRepository.findAll().stream().filter(x -> containsAllTags(tags, x)).toList();
    }

    private boolean containsAllTags(List<String> tags, Recipe recipe) {
        List<String> recipeTags = recipe.getRecipeTags().stream().map(RecipeTag::getTag).toList();
        return new HashSet<>(recipeTags).containsAll(tags.stream().map(StringExtensions::toTitleCase).toList());
    }
}
