package com.LittleDelhi.LittleDelhiBackend.mapper;

import com.LittleDelhi.LittleDelhiBackend.dto.response.RecipeIngredientResponse;
import com.LittleDelhi.LittleDelhiBackend.model.RecipeIngredient;
import org.springframework.stereotype.Component;

@Component
public class RecipeIngredientMapper {

    private final IngredientMapper ingredientMapper;

    public RecipeIngredientMapper(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }

    public RecipeIngredientResponse toResponse(RecipeIngredient ri) {
        return RecipeIngredientResponse.builder()
                .ingredient(ingredientMapper.toResponse(ri.getIngredient()))
                .quantity(ri.getQuantity())
                .build();
    }
}
