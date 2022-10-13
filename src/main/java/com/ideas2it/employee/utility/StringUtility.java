package com.ideas2it.employee.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Methods to check validation.
 * </p>
 * version 1.0
 * @author ruban 11/08/22
 **/
public class StringUtility {

    /**
     * <p>
     * This method checks for email validation
     * </p>
     *
     **/
    public static boolean isValidMail(String emailId) {
        String regex = "^[A-Za-z0-9]+(@gmail|@yahoo)+(.com|.in)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(emailId);
        return (m.find());
    }

    /**
     * <p>
     * check for adhaar validation
     * </p>
     * @param {String} adhaarNumber
     * @return boolean
     *
     * Example :If adhaar num is 12 digit it returns true otherwise false
     **/
    public static boolean isNumberValid(String adhaarNumber) {
        String regex = "[0-9]{12}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(adhaarNumber);
        return (m.find());
    }

    /**
     * <p>
     * This method checks for name validation
     * </p>
     * @param {String} employeeName
     * @return boolean
     *
     * Example :If name is less than three character or contains number it returns false otherwise true.
     **/
    public static boolean isValidName(String employeeName) {
        String regex = "[A-Za-z]{3}[A-Za-z.\s]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(employeeName);
        return (m.find());
    }

    /**
     * <p>
     *  It check for mobile number validation
     * </p>
     * @param {String} phoneNumber
     * @return boolean
     *
     * example: if userinput phoneNumber is valid, will return true orelse false
     **/
    public static boolean isValidNumber(String phoneNumber) {
        boolean temp = true;
        if (phoneNumber.length() == 10 && (phoneNumber.charAt(0) > 53 && phoneNumber.charAt(0) < 58) ) {
            for (int i = 1; i < phoneNumber.length(); i++) {
                if (!(phoneNumber.charAt(i) > 47 && phoneNumber.charAt(i) < 58)) {
                    temp = false;
                    break;
                }
            }
        } else {
            temp = false;
        }
        return temp;
    }
}