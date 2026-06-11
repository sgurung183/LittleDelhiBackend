package com.LittleDelhi.LittleDelhiBackend.mapper;

import com.LittleDelhi.LittleDelhiBackend.dto.response.InventoryResponse;
import com.LittleDelhi.LittleDelhiBackend.model.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    private final IngredientMapper ingredientMapper;

    public InventoryMapper(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }

    public InventoryResponse toResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .id(inventory.getId())
                .ingredient(ingredientMapper.toResponse(inventory.getIngredient()))
                .quantity(inventory.getQuantity())
                .threshold(inventory.getThreshold())
                .lowStock(inventory.getQuantity() <= inventory.getThreshold())
                .build();
    }
}
