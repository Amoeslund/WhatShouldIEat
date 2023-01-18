package com.amoeslund.whatshouldieat.repositories;

import com.amoeslund.whatshouldieat.repositories.entities.Recipe;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;


public interface RecipeRepository extends FirestoreReactiveRepository<Recipe> {

}