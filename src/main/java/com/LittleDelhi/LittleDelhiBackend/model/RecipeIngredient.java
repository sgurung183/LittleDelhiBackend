package com.LittleDelhi.LittleDelhiBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //manyRecipeIngrediant in One recipe
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne //manyRecipeIngrediants use the same ingredient, one ingredient used in many dishes
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private int quantity;

}
