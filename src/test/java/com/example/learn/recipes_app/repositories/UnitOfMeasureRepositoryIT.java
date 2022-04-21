package com.example.learn.recipes_app.repositories;

import com.example.learn.recipes_app.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
// IT - Integration Test
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DirtiesContext
    void findByUnitOfMeasure() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUnitOfMeasure("Teaspoon");
        unitOfMeasureOptional.ifPresent(unitOfMeasure -> assertEquals("Teaspoon", unitOfMeasure.getUnitOfMeasure()));
    }

    @Test
    void findByUnitOfMeasureTwo() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUnitOfMeasure("Cup");
        unitOfMeasureOptional.ifPresent(unitOfMeasure -> assertEquals("Cup", unitOfMeasureOptional.get().getUnitOfMeasure()));
    }
}