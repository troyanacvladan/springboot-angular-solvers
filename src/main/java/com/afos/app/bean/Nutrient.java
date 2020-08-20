package com.afos.app.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/*
* If you want to compare the members of the superclass as well,
* then use @EqualsAndHashCode(callSuper=true). If, however, you only want to compare
* fields in the current class you can use @EqualsAndHashCode(callSuper=false) which is
* the default option.*/
@Data
@EqualsAndHashCode(callSuper = true) //ovo sam dodao jer sam imao warning:https://stackoverflow.com/questions/38572566/warning-equals-hashcode-on-data-annotation-lombok-with-inheritance
@Entity
@Table(name="TBL_NUTRIENTS")
public class Nutrient extends BaseBean {

    @Column(unique = true,nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

}
