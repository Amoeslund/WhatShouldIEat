package com.amoeslund.whatshouldieat.whatshouldieat;

import com.amoeslund.whatshouldieat.repositories.entities.Recipe;
import org.junit.jupiter.api.Test;

import java.util.List;

class RecipeFetcherImplTest {

    @Test
    void getPageRecipes() {
        var crawler = new MadensVerdenRecipeFetcherImpl();
        List<Recipe> recipes = crawler.getPageRecipes();
        recipes
                .stream()
                .filter(x -> {
                            var tags = x.getRecipeTags();
                            return tags.stream().anyMatch(recipeTag -> recipeTag.tagEquals("vegetar")) &&
                                    tags.stream().anyMatch(recipeTag -> recipeTag.tagEquals("aftensmad"));
                        }

                )
                .forEach(System.out::println);
    }
}