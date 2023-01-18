package com.amoeslund.whatshouldieat.whatshouldieat;

import com.amoeslund.whatshouldieat.repositories.entities.RecipeStat;
import com.amoeslund.whatshouldieat.repositories.entities.Recipe;
import com.amoeslund.whatshouldieat.repositories.entities.RecipeTag;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ValdemarsroCrawlerImpl implements WebCrawler {
    private static final Logger log = LoggerFactory.getLogger(ValdemarsroCrawlerImpl.class);

    HashSet<String> disallowedTags = new HashSet<>(List.of(".", "Opskrifter"));

    public List<Recipe> getPageRecipes() {

        String url = "https://www.valdemarsro.dk/soeg/?terms=";
        try {
            Document doc = Jsoup.connect(url).get();

            Elements recipes = doc.select(".post-list-items").select(".post-list-item");

            return recipes
                    .parallelStream()
                    .map(this::getRecipe)
                    .filter(Objects::nonNull)
                    .toList();
        }
        catch (IOException e) {
            log.error("For '" + url + "': " + e.getMessage());
            return List.of();
        }
    }

    private Recipe getRecipe(Element element) {
        String url = element.select(".post-list-item-title").select("a").attr("href");
        if (url.isEmpty())
            return null;
        String name = element.select(".post-list-item-title").select("a").text();

        try {
            Document recipe = Jsoup.connect(url).get();
            List<RecipeTag> tags = getRecipeTags(recipe);
            List<RecipeStat> stats = getRecipeStats(recipe);
            String image = getImage(recipe);
            return new Recipe(name, url, image, tags, stats);
        } catch (IOException e) {
            log.error("Unable to get recipe " + name + " from '" + url + "': " + e.getMessage());
            return null;

        }
    }

    private String getImage(Document recipe) {
        return recipe.select(".recipe-image").select("img").attr("src");
    }


    private List<RecipeStat> getRecipeStats(Document document) {
        return document.select(".recipe-stat").stream().map(x -> {
            String label = x.select(".recipe-stat-label").text();
            String value = x.select("strong").text();
            return new RecipeStat(label, value);
        }).toList();
    }

    private List<RecipeTag> getRecipeTags(Document document) {
        return document
                .select(".recipe-bar")
                .select("a")
                .stream()
                .map(Element::text)
                .filter(s -> !disallowedTags.contains(s))
                .map(RecipeTag::new)
                .toList();
    }
}
