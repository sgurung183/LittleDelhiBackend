package com.LittleDelhi.LittleDelhiBackend.mapper;

import com.LittleDelhi.LittleDelhiBackend.dto.response.RecipeResponse;
import com.LittleDelhi.LittleDelhiBackend.model.Recipe;
import com.LittleDelhi.LittleDelhiBackend.repository.RecipeIngredientRepository;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeIngredientMapper recipeIngredientMapper;

    public RecipeMapper(RecipeIngredientRepository recipeIngredientRepository, RecipeIngredientMapper recipeIngredientMapper) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeIngredientMapper = recipeIngredientMapper;
    }

    public RecipeResponse toResponse(Recipe recipe) {
        return RecipeResponse.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .procedure(recipe.getProcedure())
                .ingredientInfo(
                        recipeIngredientRepository.findByRecipeId(recipe.getId())
                                .stream()
                                .map(recipeIngredientMapper::toResponse)
                                .toList()
                )
                .build();
    }
}
