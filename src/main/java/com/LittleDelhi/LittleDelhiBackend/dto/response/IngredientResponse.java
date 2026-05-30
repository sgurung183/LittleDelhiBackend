package com.LittleDelhi.LittleDelhiBackend.dto.response;

import com.LittleDelhi.LittleDelhiBackend.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientResponse {
    private Long id;
    private String name;
    private Unit unit;
}
