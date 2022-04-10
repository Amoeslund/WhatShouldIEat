package com.amoeslund.whatshouldieat.controllers;

import com.amoeslund.whatshouldieat.controllers.modelassemblers.RecipeModelAssembler;
import com.amoeslund.whatshouldieat.helpers.RecipeNotFoundException;
import com.amoeslund.whatshouldieat.models.RecipeDto;
import com.amoeslund.whatshouldieat.repositories.entities.Recipe;
import com.amoeslund.whatshouldieat.services.RecipeService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeModelAssembler assembler;

    public RecipeController(RecipeService recipeService, RecipeModelAssembler assembler) {

        this.recipeService = recipeService;
        this.assembler = assembler;
    }

    @GetMapping("/recipes/{id}")
    public EntityModel<RecipeDto> one(@PathVariable Long id) {

        Recipe recipe = recipeService.getRecipe(id).orElseThrow(() -> new RecipeNotFoundException(id));
        return assembler.toModel(recipe);
    }

    @GetMapping("/recipes")
    public CollectionModel<EntityModel<RecipeDto>> all() {
        return assembler.toCollectionModel(recipeService.getRecipes());
    }

    @GetMapping("/recipes/updateall")
    public CollectionModel<EntityModel<RecipeDto>> updateAll() {
        return assembler.toCollectionModel(recipeService.updateRecipes());
    }

    //get recipes by tags array
    @GetMapping("/recipes/bytags/{tags}")
    public CollectionModel<EntityModel<RecipeDto>> byTags(@PathVariable List<String> tags) {
        return assembler.toCollectionModel(recipeService.getRecipesByTags(tags));

    }


}
