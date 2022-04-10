package com.amoeslund.whatshouldieat.controllers;

import com.amoeslund.whatshouldieat.controllers.modelassemblers.RecipeTagModelAssembler;
import com.amoeslund.whatshouldieat.models.RecipeDto;
import com.amoeslund.whatshouldieat.repositories.RecipeTagRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TagsController {

    private final RecipeTagRepository recipeTagRepository;
    private final RecipeTagModelAssembler assembler;

    public TagsController(RecipeTagRepository recipeTagRepository, RecipeTagModelAssembler assembler) {
        this.recipeTagRepository = recipeTagRepository;
        this.assembler = assembler;
    }

    @GetMapping("/tags")
    public CollectionModel<EntityModel<RecipeDto.RecipeTagDto>> all() {
        return assembler.toCollectionModel(recipeTagRepository.findAll());
    }
}
