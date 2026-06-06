package com.LittleDelhi.LittleDelhiBackend.service;

import com.LittleDelhi.LittleDelhiBackend.dto.request.CreateIngredientRequest;
import com.LittleDelhi.LittleDelhiBackend.dto.request.UpdateIngredientRequest;
import com.LittleDelhi.LittleDelhiBackend.dto.response.IngredientResponse;
import com.LittleDelhi.LittleDelhiBackend.mapper.IngredientMapper;
import com.LittleDelhi.LittleDelhiBackend.model.Ingredient;
import com.LittleDelhi.LittleDelhiBackend.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public IngredientService(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }

    public IngredientResponse createIngredient(CreateIngredientRequest request) {
        Ingredient saved = ingredientRepository.save(
                Ingredient.builder()
                        .name(request.getName())
                        .costPricePerUnit(request.getCostPricePerUnit())
                        .unit(request.getUnit())
                        .build()
        );
        return ingredientMapper.toResponse(saved);
    }

    public List<IngredientResponse> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(ingredientMapper::toResponse)
                .toList();
    }

    public Optional<IngredientResponse> getIngredientById(Long id) {
        return ingredientRepository.findById(id).map(ingredientMapper::toResponse);
    }

    public Optional<IngredientResponse> updateIngredient(Long id, UpdateIngredientRequest request) {
        return ingredientRepository.findById(id).map(ingredient -> {
            ingredient.setName(request.getName());
            ingredient.setCostPricePerUnit(request.getCostPricePerUnit());
            ingredient.setUnit(request.getUnit());
            return ingredientMapper.toResponse(ingredientRepository.save(ingredient));
        });
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}
