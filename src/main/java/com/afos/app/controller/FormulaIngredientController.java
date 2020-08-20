package com.afos.app.controller;

import com.afos.app.exception.RecordNotFoundException;
import com.afos.app.service.api.FormulaIngredientService;
import com.afos.app.bean.FormulaIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="formula_ingredients")
public class FormulaIngredientController {

    final String MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @Autowired
    FormulaIngredientService formulaIngredientService;

    @GetMapping(produces = MEDIA_TYPE)
    public List<FormulaIngredient> getAllFormulaIngredient(){
        return formulaIngredientService.getAllFormulaIngrediens();
    }

    @GetMapping(path="/{id}",produces = MEDIA_TYPE)
    public FormulaIngredient getFormulaIngredientById(@PathVariable Integer id)
            throws RecordNotFoundException {
        return formulaIngredientService.getFormulaIngredientById(id);
    }

    @PostMapping(produces = MEDIA_TYPE, consumes = MEDIA_TYPE)
    public FormulaIngredient createOrUpdateFormulaIngredient(@RequestBody FormulaIngredient formulaIngredient)
            throws  RecordNotFoundException{
        return formulaIngredientService.createOrUpdateFormulaIngredient(formulaIngredient);
    }

    @DeleteMapping("/{id}")
    public void deleteFormulaIngredientById(@PathVariable Integer id) throws RecordNotFoundException{
        formulaIngredientService.deleteFormulaIngredientById(id);
    }
}
