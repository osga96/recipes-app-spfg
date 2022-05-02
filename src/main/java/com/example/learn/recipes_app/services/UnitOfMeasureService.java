package com.example.learn.recipes_app.services;

import com.example.learn.recipes_app.model.UnitOfMeasure;

import java.util.List;
import java.util.Optional;

public interface UnitOfMeasureService {

    UnitOfMeasure saveUnitOfMeasure(UnitOfMeasure unitOfMeasure);
    List<UnitOfMeasure> getAllUnitsOfMeasure();

    Optional<UnitOfMeasure> getUnitOfMeasureById(Long unitOfMeasureId);
}
