package com.LittleDelhi.LittleDelhiBackend.mapper;

import com.LittleDelhi.LittleDelhiBackend.dto.response.ItemResponse;
import com.LittleDelhi.LittleDelhiBackend.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    private final RecipeMapper recipeMapper;

    public ItemMapper(RecipeMapper recipeMapper) {
        this.recipeMapper = recipeMapper;
    }

    public ItemResponse toResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .recipe(recipeMapper.toResponse(item.getRecipe()))
                .build();
    }
}
