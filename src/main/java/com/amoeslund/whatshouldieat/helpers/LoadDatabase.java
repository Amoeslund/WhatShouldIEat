package com.amoeslund.whatshouldieat.helpers;

import com.amoeslund.whatshouldieat.repositories.RecipeRepository;
import com.amoeslund.whatshouldieat.services.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(RecipeService recipeService, RecipeRepository recipeRepository) {
        return args -> {
            recipeService.updateRecipes();
            log.info("Preloaded " + recipeRepository.count() + " recipes.");
        };
    }
}
