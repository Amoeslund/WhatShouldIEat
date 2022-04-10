package com.amoeslund.whatshouldieat.repositories;

import com.amoeslund.whatshouldieat.repositories.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;


public interface RecipeRepository extends JpaRepository<Recipe, Long>, QueryByExampleExecutor<Recipe> {
}