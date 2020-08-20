package com.afos.app.controller;

import com.afos.app.bean.Ingredient;
import com.afos.app.exception.RecordNotFoundException;
import com.afos.app.service.api.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* VJ: @RestController = @Controller + @ResponseBody.
* In case of redirect:
* Do not put the @ResponseBody annotation in the definition of your method,
* because if you do, the redirect will not work.
* */

@RestController
@RequestMapping("ingredients")
public class IngredientController {

    //Glavni service objekat kontrolera trebao bi
    //da bude constructor DI servisi (jer ima smisla da postoji u svakom
    // trenutku postojanja kontrolera).
    // Ostali servisi koji se koriste ponekad u kontroleru trebalo
    // bi da budu kao property DI servisi
    IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        Assert.notNull(ingredientService, "IngredientService obj must not be null!");
        this.ingredientService = ingredientService;
    }

    @GetMapping(produces = "application/json")
    public List<Ingredient> getAllIngredients(){
        return ingredientService.getAllIngrediens();
    }

    //VJ: @GetMapping is a shorthand for @RequestMapping(method = RequestMethod.GET)
    @GetMapping(path = "/{id}", produces = "application/json")
    public Ingredient getIngredientById(@PathVariable Integer id){
        return ingredientService.getIngredientById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    //VJ: @RequestBody -> https://www.baeldung.com/spring-request-response-body#@requestbody
    public Ingredient createOrUpdateEmployee(@RequestBody Ingredient ingredient) {
        return ingredientService.createOrUpdateIngredient(ingredient);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable("id") Integer id) throws RecordNotFoundException {
        ingredientService.deleteIngredientById(id);
    }

}
