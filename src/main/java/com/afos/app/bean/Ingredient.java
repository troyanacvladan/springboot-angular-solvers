package com.afos.app.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="TBL_INGREDIENTS")
//VJ: Without name property Hibernate by default will generate the names of the tables in lowercase letters.
//https://www.baeldung.com/spring-boot-hibernate#uppercase-table
public class Ingredient extends BaseBean {

    @Column(unique = true,nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

}

/*
*     id INT AUTO_INCREMENT  PRIMARY KEY,
    code VARCHAR(250) UNIQUE,
    name VARCHAR(250) NOT NULL,
    price DOUBLE NOT NULL*/
