/*
 * Copyright (C) 2018-2019 Mike Schulenberg
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mikeschulenbergdev.calculator.core;

import java.util.HashSet;

/**
 * This class represents the arithmetic operators used by the program.
 * 
 * @author Mike Schulenberg
 * @version 1.0.1
 */
public class ValidOperators {
	
    public static final String DIVISION = "\u00F7";
    public static final String MULTIPLICATION = "\u00D7";
    public static final String ADDITION = "+";
    public static final String SUBTRACTION = "-";
    
    private static final HashSet<String> VALID_OPERATORS;
    
    // Prevent this class from being instantiated
    private ValidOperators() {
    	
    }
    
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
