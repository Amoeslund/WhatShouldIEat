package com.amoeslund.whatshouldieat.models;

import org.springframework.hateoas.EntityModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public final class Recipe implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    private final Long id;
    private final String name;
    private final List<RecipeStat> recipeStats;
    private final String image;
    private final List<EntityModel<RecipeTag>> recipeTags;
    private final String url;

    public Recipe(Long id, String name, List<RecipeStat> recipeStats, String image,
                  List<EntityModel<RecipeTag>> recipeTags, String url) {
        this.id = id;
        this.name = name;
        this.recipeStats = recipeStats;
        this.image = image;
        this.recipeTags = recipeTags;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe entity = (Recipe) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.recipeStats, entity.recipeStats) &&
                Objects.equals(this.image, entity.image) &&
                Objects.equals(this.recipeTags, entity.recipeTags) &&
                Objects.equals(this.url, entity.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, recipeStats, image, recipeTags, url);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "recipeStats = " + recipeStats + ", " +
                "image = " + image + ", " +
                "recipeTags = " + recipeTags + ", " +
                "url = " + url + ")";
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public List<RecipeStat> recipeStats() {
        return recipeStats;
    }

    public String image() {
        return image;
    }

    public List<EntityModel<RecipeTag>> recipeTags() {
        return recipeTags;
    }

    public String url() {
        return url;
    }


    public static final class RecipeStat implements Serializable {
        @Serial
        private static final long serialVersionUID = 0L;
        private final String label;
        private final String description;

        public RecipeStat(String label, String description) {
            this.label = label;
            this.description = description;
        }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                RecipeStat entity = (RecipeStat) o;
                return Objects.equals(this.label, entity.label) &&
                        Objects.equals(this.description, entity.description);
            }

            @Override
            public String toString() {
                return getClass().getSimpleName() + "(" +
                        "label = " + label + ", " +
                        "description = " + description + ")";
            }

        public String label() {
            return label;
        }

        public String description() {
            return description;
        }

        @Override
        public int hashCode() {
            return Objects.hash(label, description);
        }

        }

    public static final class RecipeTag implements Serializable {
        @Serial
        private static final long serialVersionUID = 0L;
        private final String tag;

        public RecipeTag(String tag) {
            this.tag = tag;
        }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                RecipeTag entity = (RecipeTag) o;
                return Objects.equals(this.tag, entity.tag);
            }

            @Override
            public String toString() {
                return getClass().getSimpleName() + "(" +
                        "tag = " + tag + ")";
            }

        public String tag() {
            return tag;
        }

        @Override
        public int hashCode() {
            return Objects.hash(tag);
        }

        }
}
