package com.LittleDelhi.LittleDelhiBackend.service;

import com.LittleDelhi.LittleDelhiBackend.dto.request.CreateInventoryRequest;
import com.LittleDelhi.LittleDelhiBackend.dto.request.UpdateInventoryRequest;
import com.LittleDelhi.LittleDelhiBackend.dto.response.InventoryResponse;
import com.LittleDelhi.LittleDelhiBackend.mapper.InventoryMapper;
import com.LittleDelhi.LittleDelhiBackend.model.Ingredient;
import com.LittleDelhi.LittleDelhiBackend.model.Inventory;
import com.LittleDelhi.LittleDelhiBackend.repository.IngredientRepository;
import com.LittleDelhi.LittleDelhiBackend.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
#       [x] createInventory(CreateInventoryRequest) -> InventoryResponse
#       [x] getAllInventory() -> List<InventoryResponse>
#       [x] getInventoryById(Long id) -> InventoryResponse
#       [x] updateInventory(Long id, UpdateInventoryRequest) -> InventoryResponse
#       [x] getLowStockItems() -> List<InventoryResponse>
*/
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final IngredientRepository ingredientRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryService(InventoryRepository inventoryRepository, IngredientRepository ingredientRepository, InventoryMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.inventoryMapper = inventoryMapper;
    }

    public InventoryResponse createInventory(CreateInventoryRequest request) {
        Ingredient ingredient = ingredientRepository.findById(request.getIngredientId())
                .orElseThrow(() -> new RuntimeException("Ingredient does not exist with id: " + request.getIngredientId()));

        Inventory saved = inventoryRepository.save(
                Inventory.builder()
                        .ingredient(ingredient)
                        .quantity(request.getQuantity())
                        .threshold(request.getThreshold())
                        .build()
        );

        return inventoryMapper.toResponse(saved);
    }

    public List<InventoryResponse> getAllInventory() {
        return inventoryRepository.findAll().stream()
                .map(inventoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    public InventoryResponse getInventoryById(Long id) {
        return inventoryMapper.toResponse(inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory does not exist with id: " + id)));
    }

    public InventoryResponse updateInventory(Long id, UpdateInventoryRequest request) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory does not exist with id: " + id));

        inventory.setQuantity(request.getQuantity());
        inventory.setThreshold(request.getThreshold());

        return inventoryMapper.toResponse(inventoryRepository.save(inventory));
    }

    // filters inventory where quantity <= threshold; lowStock flag is computed in the mapper
    public List<InventoryResponse> getLowStockItems() {
        return inventoryRepository.findAll().stream()
                .filter(i -> i.getQuantity() <= i.getThreshold())
                .map(inventoryMapper::toResponse)
                .collect(Collectors.toList());
    }
}
