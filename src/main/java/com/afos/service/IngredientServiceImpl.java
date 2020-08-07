package com.afos.service;

import com.afos.bean.Ingredient;
import com.afos.dao.IngredientRepository;
import com.afos.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> getAllIngrediens() {

        List<Ingredient> employeeList = (List<Ingredient>) ingredientRepository.findAll();
        if (employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Ingredient getIngredientById(Integer id) throws RecordNotFoundException {
        Optional<Ingredient> employee = ingredientRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No ingredient record exist for given id: "+id);
        }
    }

    @Override
    public Ingredient createOrUpdateIngredient(Ingredient entity) throws RecordNotFoundException{
        Optional<Ingredient> ingredient = ingredientRepository.findById(entity.getId());

        if(ingredient.isPresent()) { //update
            Ingredient newEntity = ingredient.get();
            newEntity.setCode(entity.getCode());
            newEntity.setName(entity.getName());
            newEntity.setPrice(entity.getPrice());
            newEntity = ingredientRepository.save(newEntity);
            return newEntity;
        } else { //create
            entity = ingredientRepository.save(entity);
            return entity;
        }
    }

    @Override
    public void deleteIngredientById(Integer id) throws RecordNotFoundException{
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);

        if(ingredient.isPresent()) {
            ingredientRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No ingredient record exist for given id: "+id);
        }
    }
}
