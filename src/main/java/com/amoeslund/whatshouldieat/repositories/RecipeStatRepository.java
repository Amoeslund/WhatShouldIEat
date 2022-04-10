package com.amoeslund.whatshouldieat.repositories;

import com.amoeslund.whatshouldieat.repositories.entities.RecipeStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeStatRepository extends JpaRepository<RecipeStat, String> {
}