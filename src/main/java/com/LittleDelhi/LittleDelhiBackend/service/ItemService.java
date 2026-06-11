package com.LittleDelhi.LittleDelhiBackend.service;

import com.LittleDelhi.LittleDelhiBackend.dto.request.CreateItemRequest;
import com.LittleDelhi.LittleDelhiBackend.dto.request.UpdateItemRequest;
import com.LittleDelhi.LittleDelhiBackend.dto.response.ItemResponse;
import com.LittleDelhi.LittleDelhiBackend.mapper.ItemMapper;
import com.LittleDelhi.LittleDelhiBackend.model.Item;
import com.LittleDelhi.LittleDelhiBackend.model.Recipe;
import com.LittleDelhi.LittleDelhiBackend.repository.ItemRepository;
import com.LittleDelhi.LittleDelhiBackend.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
#       [x] createItem(CreateItemRequest) -> ItemResponse
#       [x] getAllItems() -> List<ItemResponse>
#       [x] getItemById(Long id) -> ItemResponse
#       [x] updateItem(Long id, UpdateItemRequest) -> ItemResponse
#       [x] deleteItem(Long id) -> void */
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

    public List<ItemResponse> getAllItems(){
        List<ItemResponse> returnItemList = new ArrayList<>();
        List<Item> items = itemRepository.findAll();
        items.forEach(
                item -> {
                    returnItemList.add(itemMapper.toResponse(item));
                }
        );
        return returnItemList;
    }

    public ItemResponse getItemById(Long id){
        return itemMapper.toResponse(itemRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Item does not exist with id " + id)));
    }

    public ItemResponse updateItem(Long id, UpdateItemRequest updateItemRequest){
        Item item = itemRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Item does not exist with id : " + id)
        );
        Recipe recipe = recipeRepository.findById(updateItemRequest.getRecipeId()).orElseThrow(
                () -> new RuntimeException("RECIPE DOES NOT EXIST WITH ID: " + updateItemRequest.getRecipeId()));
        item.setName(updateItemRequest.getName());
        item.setPrice(updateItemRequest.getPrice());
        item.setRecipe(recipe);
        return itemMapper.toResponse(itemRepository.save(item));

    }
    public void deleteItem(Long id){
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item does not exist with id: " + id));
        // only Item is deleted here; CascadeType.ALL on Item.recipe and Recipe.IngredientQuantityInfo
        // automatically deletes the associated Recipe and RecipeIngredient rows
        itemRepository.delete(item);
    }
}
