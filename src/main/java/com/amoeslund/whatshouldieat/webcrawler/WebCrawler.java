package com.amoeslund.whatshouldieat.webcrawler;

import com.amoeslund.whatshouldieat.repositories.entities.Recipe;

import java.util.List;

public interface WebCrawler {
    List<Recipe> getPageRecipes();
}
