package com.afos.app.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
/*
* @Data generates all the boilerplate that is normally associated with simple POJOs
* (Plain Old Java Objects) and beans: getters for all fields...
* https://projectlombok.org/features/Data*/
public abstract class BaseBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /*VJ: Imao sam problem sa GenerationType.AUTO ... TODO

    *  */


}
