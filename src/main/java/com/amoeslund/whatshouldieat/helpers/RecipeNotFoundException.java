package com.amoeslund.whatshouldieat.helpers;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(String id) {
        super("Could not find recipe with id " + id);
    }
}
