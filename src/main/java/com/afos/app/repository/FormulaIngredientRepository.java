package com.afos.app.repository;

import com.afos.app.bean.FormulaIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaIngredientRepository extends JpaRepository<FormulaIngredient,Integer> {
}
