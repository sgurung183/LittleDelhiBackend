package com.LittleDelhi.LittleDelhiBackend.mapper;

import com.LittleDelhi.LittleDelhiBackend.dto.response.IngredientResponse;
import com.LittleDelhi.LittleDelhiBackend.model.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public IngredientResponse toResponse(Ingredient ingredient) {
        return IngredientResponse.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .unit(ingredient.getUnit())
                .build();
    }
}
