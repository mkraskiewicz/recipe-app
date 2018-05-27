package com.mkraskiewicz.recipeapp.services.impl;

import com.mkraskiewicz.recipeapp.commands.UnitOfMeasureCommand;
import com.mkraskiewicz.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.mkraskiewicz.recipeapp.model.UnitOfMeasure;
import com.mkraskiewicz.recipeapp.repositories.UnitOfMeasureRepository;
import com.mkraskiewicz.recipeapp.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Maciej on 25/05/2018
 */
public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void listAllUoms() {

        Set<UnitOfMeasure> unitOfMeasures = new HashSet();

        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        Set<UnitOfMeasureCommand> uomCommands = unitOfMeasureService.listAllUoms();

        assertEquals(uomCommands.size(),2);
        verify(unitOfMeasureRepository,times(1)).findAll();
    }

    @Test
    public void saveIngredientCommand(){

    }
}