package com.LittleDelhi.LittleDelhiBackend.repository;

import com.LittleDelhi.LittleDelhiBackend.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
