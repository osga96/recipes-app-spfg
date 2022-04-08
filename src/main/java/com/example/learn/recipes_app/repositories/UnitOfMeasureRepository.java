package com.example.learn.recipes_app.repositories;

import com.example.learn.recipes_app.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    public Optional<UnitOfMeasure> findByUnitOfMeasure(String unitOfMeasure);
}
