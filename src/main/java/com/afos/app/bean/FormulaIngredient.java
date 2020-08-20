package com.afos.app.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="TBL_FORMULA_INGREDIENTS")
public class FormulaIngredient extends BaseBean {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Double min;

    private Double max;

    private Double result;

    private Double weight;
}
