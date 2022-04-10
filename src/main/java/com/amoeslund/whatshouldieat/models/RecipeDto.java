package com.amoeslund.whatshouldieat.models;

import org.springframework.hateoas.EntityModel;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public record RecipeDto(Long id, String name, List<RecipeStatDto> recipeStats, String image,
                        List<EntityModel<RecipeTagDto>> recipeTags, String url) implements Serializable {

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeDto entity = (RecipeDto) o;
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

    public static class RecipeStatDto implements Serializable {
        private final String label;
        private final String description;

        public RecipeStatDto(String label, String description) {
            this.label = label;
            this.description = description;
        }

        public String getLabel() {
            return label;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RecipeStatDto entity = (RecipeStatDto) o;
            return Objects.equals(this.label, entity.label) &&
                    Objects.equals(this.description, entity.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label, description);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "label = " + label + ", " +
                    "description = " + description + ")";
        }
    }

    public static class RecipeTagDto implements Serializable {
        private final String tag;

        public RecipeTagDto(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RecipeTagDto entity = (RecipeTagDto) o;
            return Objects.equals(this.tag, entity.tag);
        }

        @Override
        public int hashCode() {
            return Objects.hash(tag);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "tag = " + tag + ")";
        }
    }
}
