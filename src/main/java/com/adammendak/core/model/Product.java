package com.adammendak.core.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
}
