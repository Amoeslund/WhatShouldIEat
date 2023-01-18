package com.amoeslund.whatshouldieat.controllers;

import com.amoeslund.whatshouldieat.controllers.modelassemblers.RecipeTagModelAssembler;
import com.amoeslund.whatshouldieat.models.Recipe;
import com.amoeslund.whatshouldieat.repositories.RecipeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TagsController {

    private final RecipeTagModelAssembler assembler;
    private final RecipeRepository recipeRepository;

    public TagsController(RecipeTagModelAssembler assembler,
                          RecipeRepository recipeRepository) {
        this.assembler = assembler;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/tags")
    public CollectionModel<EntityModel<Recipe.RecipeTag>> all() {
        return assembler.toCollectionModel(recipeRepository.findAll().toStream().flatMap(x -> x.getRecipeTags().stream()).distinct().toList());
    }
}
