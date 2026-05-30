package com.LittleDelhi.LittleDelhiBackend.repository;

import com.LittleDelhi.LittleDelhiBackend.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByIngredientId(Long ingredientId);
}
