package com.example.recipeproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    // property name from Recipe class
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
