package com.amoeslund.whatshouldieat.repositories.entities;


import com.amoeslund.whatshouldieat.helpers.StringExtensions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "recipe_tag")
@JsonIgnoreProperties({"recipes"})

public class RecipeTag {
    @Id
    @NotNull
    private String tag;

    public RecipeTag() {

    }


    public RecipeTag(String tag) {
        this.tag = StringExtensions.toTitleCase(tag);
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = StringExtensions.toTitleCase(tag);
    }


    @Override
    public String toString() {
        return tag;
    }

    public boolean tagEquals(String s) {
        return this.getTag().equalsIgnoreCase(s);
    }

    @ManyToMany(mappedBy = "recipeTags")
    private Collection<Recipe> recipes;

    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Collection<Recipe> recipes) {
        this.recipes = recipes;
    }
}
