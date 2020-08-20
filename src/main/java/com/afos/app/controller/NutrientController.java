package com.afos.app.controller;

import com.afos.app.exception.RecordNotFoundException;
import com.afos.app.bean.Nutrient;
import com.afos.app.service.api.NutrientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="nutrients")
public class NutrientController {

    final String MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @Autowired
    NutrientService nutrientService;

    @GetMapping(produces = MEDIA_TYPE)
    public List<Nutrient> getAllNutirents(){
        return nutrientService.getAllNutirents();
    }

    @GetMapping(path="/{id}",produces = MEDIA_TYPE)
    public Nutrient getNutrientById(@PathVariable Integer id)
            throws RecordNotFoundException {
        return nutrientService.getNutrientById(id);
    }

    @PostMapping(produces = MEDIA_TYPE, consumes = MEDIA_TYPE)
    public Nutrient createOrUpdateNutrient(@RequestBody Nutrient nutrient)
            throws  RecordNotFoundException{
        return nutrientService.createOrUpdateNutrient(nutrient);
    }

    @DeleteMapping("/{id}")
    public void deleteNutrientById(@PathVariable Integer id) throws RecordNotFoundException{
        nutrientService.deleteNutrientById(id);
    }
}
