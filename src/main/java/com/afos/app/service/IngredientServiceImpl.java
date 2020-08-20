package com.afos.app.service;

import com.afos.app.exception.RecordNotFoundException;
import com.afos.app.service.api.IngredientService;
import com.afos.app.bean.Ingredient;
import com.afos.app.repository.IngredientRepository;
import org.springframework.beans.BeanUtils;
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

        List<Ingredient> entityList = ingredientRepository.findAll();
        if (entityList.size() > 0) {
            return entityList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Ingredient getIngredientById(Integer id) throws RecordNotFoundException {
        Optional<Ingredient> entity = ingredientRepository.findById(id);
        if(entity.isPresent()) {
            return entity.get();
        } else {
            throw new RecordNotFoundException("No ingredient record exist for given id: "+id);
        }
    }

    @Override
    public Ingredient createOrUpdateIngredient(Ingredient entity) throws RecordNotFoundException{
        Optional<Ingredient> ingredient = ingredientRepository.findById(entity.getId());

        if(ingredient.isPresent()) { //update
            Ingredient newEntity = ingredient.get();
           // newEntity.setCode(entity.getCode());
           // newEntity.setName(entity.getName());
           // newEntity.setPrice(entity.getPrice());
            BeanUtils.copyProperties(entity,newEntity);
            return ingredientRepository.save(newEntity);
        } else { //create
            entity = ingredientRepository.save(entity);
            return entity;
        }
    }

    @Override
    public void deleteIngredientById(Integer id) throws RecordNotFoundException{
        Optional<Ingredient> entity = ingredientRepository.findById(id);

        if(entity.isPresent()) {
            ingredientRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No ingredient record exist for given id: "+id);
        }
    }
}
