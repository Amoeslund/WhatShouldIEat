package com.amoeslund.whatshouldieat.controllers.modelassemblers;

import com.amoeslund.whatshouldieat.controllers.RecipeController;
import com.amoeslund.whatshouldieat.models.Recipe;
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

public class RecipeModelAssembler implements RepresentationModelAssembler<com.amoeslund.whatshouldieat.repositories.entities.Recipe, EntityModel<Recipe>> {

    private final RecipeTagModelAssembler recipeTagModelAssembler;

    public RecipeModelAssembler(RecipeTagModelAssembler recipeTagModelAssembler) {
        this.recipeTagModelAssembler = recipeTagModelAssembler;
    }

    @Override
    public EntityModel<Recipe> toModel(com.amoeslund.whatshouldieat.repositories.entities.Recipe recipe) {

        List<EntityModel<Recipe.RecipeTag>> tagModels = recipe.getRecipeTags().stream().map(recipeTagModelAssembler::toModel).collect(Collectors.toList());
        List<Recipe.RecipeStat> recipeStats = recipe.getRecipeStats().stream().map(recipeStat -> new Recipe.RecipeStat(recipeStat.getLabel(), recipeStat.getDescription())).toList();
        Recipe recipeDto = new Recipe(recipe.getId(), recipe.getName(), recipeStats, recipe.getImage(), tagModels, recipe.getUrl());
        return EntityModel.of(recipeDto, linkTo(methodOn(RecipeController.class).one(recipeDto.id())).withSelfRel(), linkTo(methodOn(RecipeController.class).all()).withRel("recipes"));
    }

    @Override
    public CollectionModel<EntityModel<Recipe>> toCollectionModel(Iterable<? extends com.amoeslund.whatshouldieat.repositories.entities.Recipe> entities) {
        List<EntityModel<Recipe>> recipes = new ArrayList<>();
        for (com.amoeslund.whatshouldieat.repositories.entities.Recipe entity : entities) {
            recipes.add(toModel(entity));
        }
        return CollectionModel.of(recipes, linkTo(methodOn(RecipeController.class).all()).withSelfRel());
    }
}
