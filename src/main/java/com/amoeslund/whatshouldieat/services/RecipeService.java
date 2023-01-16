package com.amoeslund.whatshouldieat.services;

import com.amoeslund.whatshouldieat.helpers.StringExtensions;
import com.amoeslund.whatshouldieat.repositories.entities.RecipeTag;
import com.amoeslund.whatshouldieat.repositories.RecipeRepository;
import com.amoeslund.whatshouldieat.repositories.entities.Recipe;
import com.amoeslund.whatshouldieat.repositories.RecipeStatRepository;
import com.amoeslund.whatshouldieat.repositories.RecipeTagRepository;
import com.amoeslund.whatshouldieat.webcrawler.WebCrawler;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {
    private final Collection<WebCrawler> webCrawlers;
    private final RecipeRepository recipeRepository;
    private final RecipeStatRepository recipeStatRepository;
    private final RecipeTagRepository recipeTagRepository;

    public RecipeService(Collection<WebCrawler> webCrawlers, RecipeRepository recipeRepository, RecipeStatRepository recipeStatRepository, RecipeTagRepository recipeTagRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeStatRepository = recipeStatRepository;
        this.recipeTagRepository = recipeTagRepository;
        this.webCrawlers = webCrawlers;
    }

    public List<Recipe> updateRecipes() {

        for (WebCrawler webCrawler : webCrawlers) {
            saveRecipes(webCrawler.getPageRecipes());
        }
        return recipeRepository.findAll();
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

    private boolean containsAllTags(@NotNull List<String> tags, Recipe recipe) {
        List<String> recipeTags = recipe.getRecipeTags().stream().map(RecipeTag::getTag).toList();
        return new HashSet<>(recipeTags).containsAll(tags.stream().map(StringExtensions::toTitleCase).toList());
    }

    public List<Recipe> getNRandomUniqueRecipes(List<String> tags, int amount) {

        List<Recipe> recipesByTags = getRecipesByTags(tags);

        long totalRecords = recipesByTags.size();
        long totalPages =
                (totalRecords % amount == 0)
                        ? (totalRecords / amount)
                        : ((totalRecords / amount) + 1);
        int pageIndex = (int) (Math.random() * totalPages);

        return recipesByTags.subList(pageIndex, pageIndex + amount);
    }
}
