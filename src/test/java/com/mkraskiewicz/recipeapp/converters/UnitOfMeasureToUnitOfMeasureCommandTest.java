package com.mkraskiewicz.recipeapp.converters;

import com.mkraskiewicz.recipeapp.commands.UnitOfMeasureCommand;
import com.mkraskiewicz.recipeapp.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Maciej on 15/05/2018
 */
public class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final String DESCRIPTION = "Test description";
    public static final Long ID_VALUE = 1l;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @Before
    public void setUp(){
        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObjectConverter() throws Exception{
        assertNull(unitOfMeasureToUnitOfMeasureCommand.convert(null));
    }

    @Test
    public void testEmptyObjectConverter() throws Exception{
        assertNotNull(unitOfMeasureToUnitOfMeasureCommand.convert((new UnitOfMeasure())));
    }
    @Test
    public void convert() throws Exception{
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID_VALUE);
        uom.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand uomc = unitOfMeasureToUnitOfMeasureCommand.convert(uom);

        //then
        assertEquals(DESCRIPTION,uomc.getDescription());
        assertEquals(ID_VALUE,uomc.getId());
    }
}