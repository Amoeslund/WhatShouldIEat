package com.amoeslund.whatshouldieat.repositories.entities;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RecipeStatId implements Serializable {
    @Id
    private String label;
    @Id
    private String description;

    public RecipeStatId() {
    }

    public RecipeStatId(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeStatId that = (RecipeStatId) o;
        return Objects.equals(label, that.label) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, description);
    }
}
