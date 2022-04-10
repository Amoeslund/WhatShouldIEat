package com.amoeslund.whatshouldieat.helpers;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(Long id) {
        super("Could not find recipe with id " + id);
    }
}
