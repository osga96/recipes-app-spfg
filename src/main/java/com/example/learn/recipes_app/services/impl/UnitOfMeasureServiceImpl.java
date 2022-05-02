package com.example.learn.recipes_app.services.impl;

import com.example.learn.recipes_app.model.UnitOfMeasure;
import com.example.learn.recipes_app.repositories.UnitOfMeasureRepository;
import com.example.learn.recipes_app.services.UnitOfMeasureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public UnitOfMeasure saveUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        return unitOfMeasureRepository.save(unitOfMeasure);
    }

    @Override
    public List<UnitOfMeasure> getAllUnitsOfMeasure() {
        return (List<UnitOfMeasure>) unitOfMeasureRepository.findAll();
    }

    @Override
    public Optional<UnitOfMeasure> getUnitOfMeasureById(Long unitOfMeasureId) {
        return unitOfMeasureRepository.findById(unitOfMeasureId);
    }
}
