package com.amoeslund.whatshouldieat.repositories.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany()
    private List<RecipeStat> recipeStats = new ArrayList<>();

    public Recipe() {

    }

    public Recipe(String name, String url, String image, List<RecipeTag> tags, List<RecipeStat> stats) {
        this.name = name;
        this.url = url;
        this.image = image;
        this.recipeTags = tags;
        this.recipeStats = stats;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;
    @ManyToMany
    private List<RecipeTag> recipeTags = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public List<RecipeTag> getRecipeTags() {
        return recipeTags;
    }

    public void setRecipeTags(List<RecipeTag> recipeTags) {
        this.recipeTags = recipeTags;
    }


    public void setRecipeStats(List<RecipeStat> recipeStats) {
        this.recipeStats = recipeStats;
    }

    public List<RecipeStat> getRecipeStats() {
        return recipeStats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return name.equals(recipe.name) && url.equals(recipe.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return this.name + ": " + this.url;
    }

}
