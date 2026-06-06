package com.LittleDelhi.LittleDelhiBackend.service;

import com.LittleDelhi.LittleDelhiBackend.dto.request.CreateRecipeRequest;
import com.LittleDelhi.LittleDelhiBackend.dto.request.UpdateRecipeRequest;
import com.LittleDelhi.LittleDelhiBackend.dto.response.RecipeResponse;
import com.LittleDelhi.LittleDelhiBackend.mapper.RecipeMapper;
import com.LittleDelhi.LittleDelhiBackend.model.Recipe;
import com.LittleDelhi.LittleDelhiBackend.model.RecipeIngredient;
import com.LittleDelhi.LittleDelhiBackend.repository.IngredientRepository;
import com.LittleDelhi.LittleDelhiBackend.repository.RecipeIngredientRepository;
import com.LittleDelhi.LittleDelhiBackend.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private RecipeIngredientRepository recipeIngredientRepository;
    private RecipeMapper recipeMapper;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeMapper = recipeMapper;
    }

    public RecipeResponse createRecipe(CreateRecipeRequest createRecipeRequest) {
        Recipe savedRecipe = Recipe.builder()
                .name(createRecipeRequest.getName())
                .procedure(createRecipeRequest.getProcedure())
                .build();
        recipeRepository.save(savedRecipe);

        createRecipeRequest.getIngredients().forEach(ri -> {
            ingredientRepository.findById(ri.getIngredientId())
                    .ifPresent(ingredient -> {
                        RecipeIngredient recipeIngredient = RecipeIngredient.builder()
                                .recipe(savedRecipe)
                                .ingredient(ingredient)
                                .quantity(ri.getQuantity())
                                .build();
                        recipeIngredientRepository.save(recipeIngredient);
                    });
        });

        return recipeMapper.toResponse(savedRecipe);
    }

    public List<RecipeResponse> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(recipeMapper::toResponse)
                .toList();
    }

    public RecipeResponse getRecipeById(Long id){
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Recipe not found with id: " + id));
        return recipeMapper.toResponse(recipe);
    }

    public RecipeResponse updateRecipe(Long id, UpdateRecipeRequest recipeUpdateRequest) {
        //fetch the existing recipe and update its name and procedure in the Recipe table
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + id));
        recipe.setName(recipeUpdateRequest.getName());
        recipe.setProcedure(recipeUpdateRequest.getProcedure());
        recipeRepository.save(recipe);

        // convert each RecipeIngredientRequest into a RecipeIngredient entity
        // reuse the fetched recipe so we don't hit the DB again per ingredient
        List<RecipeIngredient> riOriginal = recipeUpdateRequest.getIngredients().stream()
                .map(recipeIngredientRequest -> RecipeIngredient.builder()
                        .ingredient(ingredientRepository.findById(recipeIngredientRequest.getIngredientId())
                                .orElseThrow(() -> new RuntimeException("Ingredient with id " + recipeIngredientRequest.getIngredientId() + " does not exist")))
                        .recipe(recipe)
                        .quantity(recipeIngredientRequest.getQuantity())
                        .build())
                .toList();

        // delete the old RecipeIngredient rows for this recipe, then save the new ones
        // RecipeIngredient owns the relationship (it holds recipe_id), so we manage it here
        recipeIngredientRepository.deleteByRecipeId(id);
        riOriginal.forEach(ri -> recipeIngredientRepository.save(ri));

        return recipeMapper.toResponse(recipe);
    }

    public void deleteRecipe(Long id){
        if(recipeRepository.existsById(id)){
            recipeIngredientRepository.deleteByRecipeId(id);
            recipeRepository.deleteById(id);
        }
    }

}
