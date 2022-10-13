package com.ideas2it.employee.utility;

import java.time.LocalDate;

/**
 * <p>
 * It has method to check whether future date or not.
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 **/
public class DateUtility {

    /**
     * <p>
     * This method used to check whether given date is future date or not.
     * </p>
     * @param {@link LocalDate} date
     **/
    public static boolean isFutureDate(LocalDate date) {
        return (date.compareTo(LocalDate.now()) > 0);
    }
}
