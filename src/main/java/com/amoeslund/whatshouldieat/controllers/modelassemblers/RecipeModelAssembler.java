package com.amoeslund.whatshouldieat.controllers.modelassemblers;

import com.amoeslund.whatshouldieat.controllers.RecipeController;
import com.amoeslund.whatshouldieat.repositories.entities.Recipe;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component

public class RecipeModelAssembler implements RepresentationModelAssembler<Recipe, EntityModel<com.amoeslund.whatshouldieat.models.Recipe>> {

    private final RecipeTagModelAssembler recipeTagModelAssembler;

    public RecipeModelAssembler(RecipeTagModelAssembler recipeTagModelAssembler) {
        this.recipeTagModelAssembler = recipeTagModelAssembler;
    }

    @Override
    public EntityModel<com.amoeslund.whatshouldieat.models.Recipe> toModel(Recipe recipe) {

        List<EntityModel<com.amoeslund.whatshouldieat.models.Recipe.RecipeTag>> tagModels = recipe.getRecipeTags().stream().map(recipeTagModelAssembler::toModel).collect(Collectors.toList());
        List<com.amoeslund.whatshouldieat.models.Recipe.RecipeStat> recipeStats = recipe.getRecipeStats().stream().map(recipeStat -> new com.amoeslund.whatshouldieat.models.Recipe.RecipeStat(recipeStat.getLabel(), recipeStat.getDescription())).toList();
        com.amoeslund.whatshouldieat.models.Recipe recipeDto = new com.amoeslund.whatshouldieat.models.Recipe(recipe.getId(), recipe.getName(), recipeStats, recipe.getImage(), tagModels, recipe.getUrl());
        return EntityModel.of(recipeDto, WebMvcLinkBuilder.linkTo(methodOn(RecipeController.class).one(recipeDto.id())).withSelfRel(), linkTo(methodOn(RecipeController.class).all()).withRel("recipes"));
    }

    @Override
    public CollectionModel<EntityModel<com.amoeslund.whatshouldieat.models.Recipe>> toCollectionModel(Iterable<? extends Recipe> entities) {
        List<EntityModel<com.amoeslund.whatshouldieat.models.Recipe>> recipes = new ArrayList<>();
        for (Recipe entity : entities) {
            recipes.add(toModel(entity));
        }
        return CollectionModel.of(recipes, linkTo(methodOn(RecipeController.class).all()).withSelfRel());
    }
}
