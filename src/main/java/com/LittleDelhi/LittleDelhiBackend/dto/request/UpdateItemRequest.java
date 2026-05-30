package com.LittleDelhi.LittleDelhiBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateItemRequest {
    private String name;
    private double price;
    private Long recipeId;
}
