package com.amoeslund.whatshouldieat.whatshouldieat;

import com.amoeslund.whatshouldieat.repositories.entities.Recipe;

import java.util.List;

public interface RecipeFetcher {
    List<Recipe> getPageRecipes();
}
