package com.amoeslund.whatshouldieat.services;

import com.amoeslund.whatshouldieat.repositories.RecipeRepository;
import com.amoeslund.whatshouldieat.helpers.StringExtensions;
import com.amoeslund.whatshouldieat.repositories.entities.RecipeTag;
import com.amoeslund.whatshouldieat.repositories.entities.Recipe;
import com.amoeslund.whatshouldieat.whatshouldieat.WebCrawler;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RecipeService {
    private final Collection<WebCrawler> webCrawlers;
    private final RecipeRepository recipeRepository;

    public RecipeService(Collection<WebCrawler> webCrawlers, RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
        this.webCrawlers = webCrawlers;
    }

    public long updateRecipes() {

        long totalCount = 0;
        for (WebCrawler webCrawler : webCrawlers) {
            totalCount += saveRecipes(webCrawler.getPageRecipes()).size();
        }
        return totalCount;
    }

    private List<Recipe> saveRecipes(List<Recipe> recipes) {
        return recipeRepository.saveAll(recipes).collect(Collectors.toList()).block();
    }

    public List<Recipe> getRecipes() {
        return recipeRepository.findAll().collect(Collectors.toList()).block();
    }

    public Optional<Recipe> getRecipe(String id) {
        return recipeRepository.findById(id).blockOptional();
    }

    public List<Recipe> getRecipesByTags(List<String> tags) {
        return getRecipesByTagsInternal(tags).collect(Collectors.toList());
    }

    public Stream<Recipe> getRecipesByTagsInternal(List<String> tags) {
        return recipeRepository.findAll().toStream().filter(x -> containsAllTags(tags, x));
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
        Collections.shuffle(recipesByTags);
        return recipesByTags.subList(pageIndex, pageIndex + amount);
    }
}
