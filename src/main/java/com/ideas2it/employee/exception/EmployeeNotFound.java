package com.ideas2it.employee.exception;

/**
 * <h1> NoEmployeeIdFoundException </h1>
 * <p>
 * It implements exception method for employee id.
 * </P>
 *
 * version 1.0
 * @author ruban 11/08/22
 **/
public class EmployeeNotFound extends Exception {

    /**
     * <p>
     * This method shows message of exception thrown.
     * </p>
     **/
    public EmployeeNotFound(String message) {
        super(message);
    }
}


