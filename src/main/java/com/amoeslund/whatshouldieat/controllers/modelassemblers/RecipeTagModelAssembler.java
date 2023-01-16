package com.amoeslund.whatshouldieat.controllers.modelassemblers;

import com.amoeslund.whatshouldieat.controllers.RecipeController;
import com.amoeslund.whatshouldieat.controllers.TagsController;
import com.amoeslund.whatshouldieat.models.Recipe;
import com.amoeslund.whatshouldieat.repositories.entities.RecipeTag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecipeTagModelAssembler implements RepresentationModelAssembler<RecipeTag, EntityModel<Recipe.RecipeTag>> {

    @Override
    public EntityModel<Recipe.RecipeTag> toModel(RecipeTag entity) {
        Recipe.RecipeTag tagDto = new Recipe.RecipeTag(entity.getTag());
        return EntityModel.of(tagDto,
                linkTo(methodOn(RecipeController.class).byTags(List.of(tagDto.tag()))).withSelfRel(),
                linkTo(methodOn(RecipeController.class).randomByTags(List.of(tagDto.tag()))).withRel("randomByTag"),
                linkTo(methodOn(TagsController.class).all()).withRel("tags"));
    }

    @Override
    public CollectionModel<EntityModel<Recipe.RecipeTag>> toCollectionModel(Iterable<? extends RecipeTag> entities) {
        List<EntityModel<Recipe.RecipeTag>> tags = new ArrayList<>();
        for (RecipeTag entity : entities) {
            tags.add(toModel(entity));
        }

        return CollectionModel.of(tags, linkTo(methodOn(TagsController.class).all()).withSelfRel());
    }
}
