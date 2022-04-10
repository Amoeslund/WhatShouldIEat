package com.amoeslund.whatshouldieat.controllers.modelassemblers;

import com.amoeslund.whatshouldieat.controllers.RecipeController;
import com.amoeslund.whatshouldieat.controllers.TagsController;
import com.amoeslund.whatshouldieat.models.RecipeDto;
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
public class RecipeTagModelAssembler implements RepresentationModelAssembler<RecipeTag, EntityModel<RecipeDto.RecipeTagDto>> {

    @Override
    public EntityModel<RecipeDto.RecipeTagDto> toModel(RecipeTag entity) {
        RecipeDto.RecipeTagDto tagDto = new RecipeDto.RecipeTagDto(entity.getTag());
        return EntityModel.of(tagDto,
                linkTo(methodOn(RecipeController.class).byTags(List.of(tagDto.getTag()))).withSelfRel(),
                linkTo(methodOn(TagsController.class).all()).withRel("tags"));
    }

    @Override
    public CollectionModel<EntityModel<RecipeDto.RecipeTagDto>> toCollectionModel(Iterable<? extends RecipeTag> entities) {
        List<EntityModel<RecipeDto.RecipeTagDto>> tags = new ArrayList<>();
        for (RecipeTag entity : entities) {
            tags.add(toModel(entity));
        }

        return CollectionModel.of(tags, linkTo(methodOn(TagsController.class).all()).withSelfRel());
    }
}
