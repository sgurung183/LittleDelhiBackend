package com.LittleDelhi.LittleDelhiBackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeResponse {
    private Long id;
    private String name;
    private List<RecipeIngredientResponse> ingredientInfo;
    private String procedure;

}
