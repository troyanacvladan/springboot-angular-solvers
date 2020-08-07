package com.afos.service;

import com.afos.bean.Ingredient;
import com.afos.exception.RecordNotFoundException;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getAllIngrediens();
    Ingredient getIngredientById(Integer id) throws RecordNotFoundException;
    Ingredient createOrUpdateIngredient(Ingredient entity);
    void deleteIngredientById(Integer id);
}
