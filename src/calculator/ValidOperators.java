/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.HashSet;

/**
 * This class represents the arithmetic operators used by the application.
 * 
 * @author Mike Schulenberg
 */
public class ValidOperators {
    public static final String DIVISION = "\u00F7";
    public static final String MULTIPLICATION = "\u00D7";
    public static final String ADDITION = "+";
    public static final String SUBTRACTION = "-";
    
    private static final HashSet<String> VALID_OPERATORS;
    
    static {
        VALID_OPERATORS = new HashSet<>();
        VALID_OPERATORS.add(DIVISION);
        VALID_OPERATORS.add(MULTIPLICATION);
        VALID_OPERATORS.add(ADDITION);
        VALID_OPERATORS.add(SUBTRACTION);
    }
    
    /**
     * Determines if a String is a valid arithmetic operator.
     * 
     * @param key The String to be evaluated.
     * @return True is `key` is a valid operator; false otherwise.
     */
    public static boolean isOperator(String key) {
        return VALID_OPERATORS.contains(key);
    }
}
