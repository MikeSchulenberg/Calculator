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
public class Operators {
    private final String DIVISION = "\u00F7";
    private final String MULTIPLICATION = "\u00D7";
    private final String ADDITION = "+";
    private final String SUBTRACTION = "-";
    
    private HashSet<String> validOperators;
    
    public Operators() {
        prepValidOperators();
    }
    
    /**
     * Loads the operator Strings into a structure for ease of reference.
     */
    private void prepValidOperators() {
        validOperators = new HashSet<>();
        validOperators.add(DIVISION);
        validOperators.add(MULTIPLICATION);
        validOperators.add(ADDITION);
        validOperators.add(SUBTRACTION);
    }
    
    /**
     * Returns the division sign.
     * 
     * @return A String representation of an operator.
     */
    public String getDivisionSign() {
        return DIVISION;
    }
    
    /**
     * Returns the multiplication sign.
     * 
     * @return A String representation of an operator.
     */
    public String getMultiplicationSign() {
        return MULTIPLICATION;
    }
    
    /**
     * Returns the addition sign.
     * 
     * @return A String representation of an operator.
     */
    public String getAdditionSign() {
        return ADDITION;
    }
    
    /**
     * Returns the subtraction sign.
     * 
     * @return A String representation of an operator.
     */
    public String getSubtractionSign() {
        return SUBTRACTION;
    }
    
    /**
     * Determines if a String is a valid arithmetic operator.
     * 
     * @param key The String to be evaluated.
     * @return True is `key` is a valid operator; false otherwise.
     */
    public boolean isOperator(String key) {
        return validOperators.contains(key);
    }
}
