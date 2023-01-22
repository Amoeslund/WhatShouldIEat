package com.amoeslund.whatshouldieat.whatshouldieat;

import com.amoeslund.whatshouldieat.repositories.entities.Recipe;
import com.amoeslund.whatshouldieat.repositories.entities.RecipeStat;
import com.amoeslund.whatshouldieat.repositories.entities.RecipeTag;
import com.google.common.collect.ImmutableList;
import com.google.schemaorg.JsonLdSerializer;
import com.google.schemaorg.SchemaOrgType;
import com.google.schemaorg.core.CoreFactory;
import com.google.schemaorg.core.RecipeImpl;
import com.google.schemaorg.core.Thing;
import com.google.schemaorg.core.datatype.Text;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class MadensVerdenRecipeFetcherImpl implements RecipeFetcher {

    private static final Logger log = LoggerFactory.getLogger(ValdemarRecipeFetcherImpl.class);
    JsonLdSerializer serializer = new JsonLdSerializer(true);

    HashSet<String> disallowedTags = new HashSet<>(List.of(".", "Opskrifter"));

    public List<Recipe> getPageRecipes() {

        String url = "https://madensverden.dk/sitemap_index.xml";
        try {
            Document doc = Jsoup.connect(url).get();

            List<String> sitemapUrls = doc.children().select("sitemapindex").select("sitemap").select("loc").eachText();

            List<String> recipeUrls = sitemapUrls
                    .parallelStream()
                    .map(this::getSiteMapRecipes)
                    .filter(Objects::nonNull)
                    .flatMap(Collection::stream)
                    .toList();

            return recipeUrls
                    .parallelStream()
                    .filter(Objects::nonNull)
                    .map(this::getRecipe)
                    .toList();

        } catch (IOException e) {
            log.error("For '" + url + "': " + e.getMessage());
            return List.of();
        }
    }

    private List<String> getSiteMapRecipes(String url) {
        if (!url.contains("post-sitemap"))
            return new ArrayList<>();
        try {
            Document sitemap = Jsoup.connect(url).get();
            return sitemap.children().select("url").select("loc").eachText();
        } catch (IOException e) {
            log.error("Unable to get sitemap from '" + url + "': " + e.getMessage());
            return null;
        }
    }


    private Recipe getRecipe(String url) {
        try {
            Optional<String> first = getRecipeJson(url);
            if (first.isEmpty())
                return null;
            String json = first.get();
            List<Thing> deserialize = serializer.deserialize(json.replace("HowToStep", "ItemList"));
            RecipeImpl recipe = (RecipeImpl) deserialize.get(0);
            var nameList = (Text) recipe.getNameList().get(0);
            System.out.println(nameList.getValue());
            Text image = (Text) recipe.getImageList().get(0);
            return new Recipe(nameList.getValue(), url, image.getValue(), new ArrayList<>(), new ArrayList<>());
        } catch (Exception ignored) {
            return null;

        }
    }

    @NotNull
    private static Optional<String> getRecipeJson(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        List<String> applicationTexts = document.getElementsByAttributeValue("type", "application/ld+json").dataNodes().stream().map(DataNode::getWholeData).toList();
        return applicationTexts.stream().filter(x -> x.contains("\"@type\":\"Recipe\"")).findFirst();
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
