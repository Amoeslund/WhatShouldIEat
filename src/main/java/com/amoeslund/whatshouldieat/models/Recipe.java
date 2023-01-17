package com.amoeslund.whatshouldieat.models;

import org.springframework.hateoas.EntityModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public record Recipe(Long id, String name, List<RecipeStat> recipeStats, String image,
                     List<EntityModel<RecipeTag>> recipeTags, String url) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

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


    public record RecipeStat(String label, String description) implements Serializable {
            @Serial
            private static final long serialVersionUID = 0L;

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

    }

    public record RecipeTag(String tag) implements Serializable {
            @Serial
            private static final long serialVersionUID = 0L;

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

    }
}
