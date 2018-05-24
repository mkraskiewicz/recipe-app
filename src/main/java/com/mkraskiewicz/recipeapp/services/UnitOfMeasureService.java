package com.mkraskiewicz.recipeapp.services;

import com.mkraskiewicz.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by Maciej on 24/05/2018
 */
public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
