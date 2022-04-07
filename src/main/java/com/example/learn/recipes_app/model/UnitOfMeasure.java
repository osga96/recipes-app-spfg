package com.example.learn.recipes_app.model;

import javax.persistence.*;

@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String unitOfMeasure;
    @OneToOne
    private Ingredient ingredient;
}
