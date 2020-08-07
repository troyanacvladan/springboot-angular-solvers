package com.afos.dao;

import com.afos.bean.FormulaIngredient;
import com.afos.bean.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaIngredientRepository extends CrudRepository<FormulaIngredient,Integer> {
}
