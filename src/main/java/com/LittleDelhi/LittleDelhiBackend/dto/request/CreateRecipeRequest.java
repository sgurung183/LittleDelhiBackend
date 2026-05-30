package com.LittleDelhi.LittleDelhiBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeRequest {
    private String name;
    private String procedure;
    private List<RecipeIngredientRequest> ingredients;
}
