package com.LittleDelhi.LittleDelhiBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // cascade = ALL propagates delete to RecipeIngredient rows; orphanRemoval = true cleans up any RecipeIngredient that loses its Recipe owner
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> IngredientQuantityInfo;
    //is just a JPA convenience. when you fetch a recipe,
    //JPA runs a query behind the scenes like
    // SELECT * FROM recipe_ingredient WHERE recipe_id = ? and populates that list for you

    private String procedure;

}
