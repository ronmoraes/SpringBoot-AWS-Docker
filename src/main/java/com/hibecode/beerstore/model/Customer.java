package com.hibecode.beerstore.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Customer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column
    String name;
    @Column
    Integer age;

    public Customer() {}

    public Customer(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

}
