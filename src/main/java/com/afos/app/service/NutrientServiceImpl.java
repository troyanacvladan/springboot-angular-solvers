package com.afos.app.service;

import com.afos.app.exception.RecordNotFoundException;
import com.afos.app.bean.Nutrient;
import com.afos.app.repository.NutrientRepository;
import com.afos.app.service.api.NutrientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NutrientServiceImpl implements NutrientService {

    @Autowired
    NutrientRepository nutrientRepository;

    @Override
    public List<Nutrient> getAllNutirents() {
        List<Nutrient> entityList = nutrientRepository.findAll();
        if (entityList.size() > 0) {
            return entityList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Nutrient getNutrientById(Integer id) throws RecordNotFoundException {
        Optional<Nutrient> entity = nutrientRepository.findById(id);
        if(entity.isPresent()) {
            return entity.get();
        } else {
            throw new RecordNotFoundException("No Nutrient record exist for given id: "+id);
        }
    }

    @Override
    public Nutrient getNutrientByName(String name) throws RecordNotFoundException {
        Optional<Nutrient> entity = nutrientRepository.findByName(name);
        if(entity.isPresent()) {
            return entity.get();
        } else {
            throw new RecordNotFoundException("No Nutrient record exist for given name: "+name);
        }
    }

    @Override
    public Nutrient createOrUpdateNutrient(Nutrient entity) throws RecordNotFoundException {
        Optional<Nutrient> nutrient = nutrientRepository.findById(entity.getId());

        if(nutrient.isPresent()) { //update
            Nutrient newEntity = nutrient.get();
            BeanUtils.copyProperties(entity, newEntity);
            //newEntity.setCode(entity.getCode());
            //newEntity.setName(entity.getName());
            return nutrientRepository.save(newEntity);
        } else { //create
            return nutrientRepository.save(entity);
        }
    }

    @Override
    public void deleteNutrientById(Integer id) throws RecordNotFoundException {
        Optional<Nutrient> entity = nutrientRepository.findById(id);

        if(entity.isPresent()) {
            nutrientRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Nutrient record exist for given id: "+id);
        }
    }
}
