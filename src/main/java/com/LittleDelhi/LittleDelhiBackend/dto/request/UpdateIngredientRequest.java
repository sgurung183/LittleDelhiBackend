package com.LittleDelhi.LittleDelhiBackend.dto.request;

import com.LittleDelhi.LittleDelhiBackend.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIngredientRequest {
    private String name;
    private double costPricePerUnit;
    private Unit unit;
}
