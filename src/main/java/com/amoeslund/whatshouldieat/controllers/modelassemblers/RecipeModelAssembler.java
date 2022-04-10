package com.amoeslund.whatshouldieat.controllers.modelassemblers;

import com.amoeslund.whatshouldieat.controllers.RecipeController;
import com.amoeslund.whatshouldieat.models.RecipeDto;
import com.amoeslund.whatshouldieat.repositories.entities.Recipe;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component

public class RecipeModelAssembler implements RepresentationModelAssembler<Recipe, EntityModel<RecipeDto>> {

    private final RecipeTagModelAssembler recipeTagModelAssembler;

    public RecipeModelAssembler(RecipeTagModelAssembler recipeTagModelAssembler) {
        this.recipeTagModelAssembler = recipeTagModelAssembler;
    }

    @Override
    public EntityModel<RecipeDto> toModel(Recipe recipe) {

        List<EntityModel<RecipeDto.RecipeTagDto>> tagModels = recipe.getRecipeTags().stream().map(recipeTagModelAssembler::toModel).collect(Collectors.toList());
        List<RecipeDto.RecipeStatDto> recipeStats = recipe.getRecipeStats().stream().map(recipeStat -> new RecipeDto.RecipeStatDto(recipeStat.getLabel(), recipeStat.getDescription())).toList();
        RecipeDto recipeDto = new RecipeDto(recipe.getId(), recipe.getName(), recipeStats, recipe.getImage(), tagModels, recipe.getUrl());
        return EntityModel.of(recipeDto, linkTo(methodOn(RecipeController.class).one(recipeDto.id())).withSelfRel(), linkTo(methodOn(RecipeController.class).all()).withRel("recipes"));
    }

    @Override
    public CollectionModel<EntityModel<RecipeDto>> toCollectionModel(Iterable<? extends Recipe> entities) {
        List<EntityModel<RecipeDto>> recipes = new ArrayList<>();
        for (Recipe entity : entities) {
            recipes.add(toModel(entity));
        }
        return CollectionModel.of(recipes, linkTo(methodOn(RecipeController.class).all()).withSelfRel());
    }
}
