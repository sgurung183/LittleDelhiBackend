package com.LittleDelhi.LittleDelhiBackend.dto.request;

import com.LittleDelhi.LittleDelhiBackend.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIngredientRequest {
    private String name;

    private double costPricePerUnit; //cost per unit

    private Unit unit; //the unit ingredient is measured in
}
