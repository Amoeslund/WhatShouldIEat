package com.amoeslund.whatshouldieat.repositories;

import com.amoeslund.whatshouldieat.repositories.entities.RecipeTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeTagRepository extends JpaRepository<RecipeTag, String> {
}