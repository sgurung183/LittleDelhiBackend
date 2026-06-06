package com.LittleDelhi.LittleDelhiBackend.service;

import com.LittleDelhi.LittleDelhiBackend.dto.request.CreateItemRequest;
import com.LittleDelhi.LittleDelhiBackend.dto.response.ItemResponse;
import com.LittleDelhi.LittleDelhiBackend.mapper.ItemMapper;
import com.LittleDelhi.LittleDelhiBackend.model.Item;
import com.LittleDelhi.LittleDelhiBackend.model.Recipe;
import com.LittleDelhi.LittleDelhiBackend.repository.ItemRepository;
import com.LittleDelhi.LittleDelhiBackend.repository.RecipeRepository;
import org.springframework.stereotype.Service;

/*
#       [x] createItem(CreateItemRequest) -> ItemResponse
#       [ ] getAllItems() -> List<ItemResponse>
#       [ ] getItemById(Long id) -> ItemResponse
#       [ ] updateItem(Long id, UpdateItemRequest) -> ItemResponse
#       [ ] deleteItem(Long id) -> void */
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final RecipeRepository recipeRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, RecipeRepository recipeRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.recipeRepository = recipeRepository;
        this.itemMapper = itemMapper;
    }

    public ItemResponse createItem(CreateItemRequest request) {
        Recipe recipe = recipeRepository.findById(request.getRecipeId())
                .orElseThrow(() -> new RuntimeException("Recipe does not exist. ID: " + request.getRecipeId()));

        Item saved = itemRepository.save(
                Item.builder()
                        .name(request.getName())
                        .price(request.getPrice())
                        .recipe(recipe)
                        .build()
        );

        return itemMapper.toResponse(saved);
    }
}
