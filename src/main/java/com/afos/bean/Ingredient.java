package com.afos.bean;

import javax.persistence.*;

@Entity
@Table(name="TBL_INGREDIENTS")
public class Ingredient {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true,nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}

/*
*     id INT AUTO_INCREMENT  PRIMARY KEY,
    code VARCHAR(250) UNIQUE,
    name VARCHAR(250) NOT NULL,
    price DOUBLE NOT NULL*/
