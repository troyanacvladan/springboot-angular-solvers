package com.afos.app.service;

import com.afos.app.bean.FormulaIngredient;
import com.afos.app.exception.RecordNotFoundException;
import com.afos.app.repository.FormulaIngredientRepository;
import com.afos.app.service.api.FormulaIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormulaIngredientServiceImpl implements FormulaIngredientService {

    @Autowired
    FormulaIngredientRepository formulaIngredientRepository;

    @Override
    public List<FormulaIngredient> getAllFormulaIngrediens() {
        List<FormulaIngredient> entityList = formulaIngredientRepository.findAll();
        if (entityList.size() > 0) {
            return entityList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public FormulaIngredient getFormulaIngredientById(Integer id) throws RecordNotFoundException {
        Optional<FormulaIngredient> entity = formulaIngredientRepository.findById(id);
        if(entity.isPresent()) {
            return entity.get();
        } else {
            throw new RecordNotFoundException("No FormulaIngredient record exist for given id: "+id);
        }
    }

    @Override
    public FormulaIngredient createOrUpdateFormulaIngredient(FormulaIngredient entity) throws RecordNotFoundException {
        Optional<FormulaIngredient> formulaIngredient = formulaIngredientRepository.findById(entity.getId());

        if(formulaIngredient.isPresent()) { //update
            FormulaIngredient newEntity = formulaIngredient.get();
            newEntity.setMin(entity.getMin());
            newEntity.setMax(entity.getMax());
            newEntity.setResult(entity.getResult());
            newEntity.setWeight(entity.getWeight());
           // newEntity.setIngredient(entity.getIngredient()); TODO: Videti kako se ovo kopira i da li se kopira
            return formulaIngredientRepository.save(newEntity);
        } else { //create
            return formulaIngredientRepository.save(entity);
        }
    }

    @Override
    public void deleteFormulaIngredientById(Integer id) throws RecordNotFoundException {
        Optional<FormulaIngredient> entity = formulaIngredientRepository.findById(id);

        if(entity.isPresent()) {
            formulaIngredientRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No FormulaIngredient record exist for given id: "+id);
        }
    }
}
