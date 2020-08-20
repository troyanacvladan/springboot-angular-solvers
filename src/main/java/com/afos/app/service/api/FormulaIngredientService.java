package com.afos.app.service.api;

import com.afos.app.exception.RecordNotFoundException;
import com.afos.app.bean.FormulaIngredient;

import java.util.List;

public interface FormulaIngredientService {
    List<FormulaIngredient> getAllFormulaIngrediens();
    FormulaIngredient getFormulaIngredientById(Integer id) throws RecordNotFoundException;
    FormulaIngredient createOrUpdateFormulaIngredient(FormulaIngredient entity) throws RecordNotFoundException;
    void deleteFormulaIngredientById(Integer id) throws RecordNotFoundException;
}
