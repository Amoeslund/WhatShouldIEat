package com.amoeslund.whatshouldieat.repositories.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "recipe_stat")
@IdClass(RecipeStatId.class)
public class RecipeStat {

    @Id

    private String label;

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }


    @Id
    private String description;


    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return description;
    }

    public RecipeStat(String label, String value) {
        this.label = label;
        this.description = value;
    }

    public RecipeStat() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeStat that = (RecipeStat) o;
        return Objects.equals(label, that.label) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, description);
    }
}
