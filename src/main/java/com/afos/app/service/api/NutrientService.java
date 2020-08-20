package com.afos.app.service.api;

import com.afos.app.exception.RecordNotFoundException;
import com.afos.app.bean.Nutrient;

import java.util.List;

public interface NutrientService {
    List<Nutrient> getAllNutirents();
    Nutrient getNutrientById(Integer id) throws RecordNotFoundException;
    Nutrient createOrUpdateNutrient(Nutrient entity) throws RecordNotFoundException;
    void deleteNutrientById(Integer id) throws RecordNotFoundException;
    Nutrient getNutrientByName(String name) throws RecordNotFoundException;
}
