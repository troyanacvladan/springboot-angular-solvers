package com.afos.app.service.api;

import com.afos.app.exception.RecordNotFoundException;
import com.afos.app.bean.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getAllIngrediens();
    Ingredient getIngredientById(Integer id) throws RecordNotFoundException;
    Ingredient createOrUpdateIngredient(Ingredient entity) throws RecordNotFoundException;
    void deleteIngredientById(Integer id) throws RecordNotFoundException;
}
