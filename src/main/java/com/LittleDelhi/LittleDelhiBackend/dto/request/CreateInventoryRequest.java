package com.LittleDelhi.LittleDelhiBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInventoryRequest {
    private Long ingredientId;
    private int quantity;
    private int threshold;
}
