package com.LittleDelhi.LittleDelhiBackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {
    private Long id;
    private IngredientResponse ingredient;
    private int quantity;
    private int threshold;
    private boolean lowStock;
}
