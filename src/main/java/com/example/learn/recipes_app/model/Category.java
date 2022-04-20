package com.example.learn.recipes_app.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String categoryName;
    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    private List<Recipe> recipes;

}
