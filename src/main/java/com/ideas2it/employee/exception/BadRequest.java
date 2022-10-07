package com.ideas2it.employee.exception;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * It has custom exception method
 * </p>
 *
 * @author Ruban
 * @version 1.0 12/08/22
 *
 **/
public class BadRequest extends RuntimeException {
    public List<Integer> validationErrorAsList = new ArrayList<>();

    /**
     * <p>
     * This method for showing exception from list of parametrised constructors.
     * </p>
     * @param {@link String} message
     * @param {@link List<Integer>} validationErrorAsList
     **/
    public BadRequest(String message, List<Integer> validationErrorAsList) {
        super(message);
        this.validationErrorAsList = validationErrorAsList;
    }

    /**
     * <p>
     * This method used for showing exception of validation error
     * </p>
     **/
    public List<Integer> getValidationErrorAsList() {
        return validationErrorAsList;
    }
}
