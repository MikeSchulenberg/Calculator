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

import java.math.BigDecimal;
import java.util.Stack;

import org.springframework.stereotype.Component;

/**
 * This class evaluates arithmetic expressions.
 * 
 * @author Mike Schulenberg
 * @version 1.0.0
 */

@Component
public class ExpressionEvaluator{
	
    private final Stack<BigDecimal> VALUES = new Stack<>();
    private final Stack<Character> OPERATORS = new Stack<>();
    
    /**
     * Evaluates an arithmetic expression according to operator precedence.
     * 
     * @param expr The expression to be evaluated.
     * @return The result the expression evaluates to.
     * @throws Exception When parsing of an expression fails, when a "divide
     * by 0" error occurs, or when a bad decimal number is parsed.
     */
    public String calculate(String expr) throws Exception {              
        parseExpression(expr);
        
        while (!OPERATORS.empty()) {
            evaluateSubexpression();
        }
        
        BigDecimal result = VALUES.pop();
        
        return result.toString();
    }
    
    /**
     * Parses the expression into a stack of integers and a stack of characters.
     * 
     * @param expr The expression to be evaluated.
     * @throws Exception When parsing of an expression fails, when a "divide
     * by 0" error occurs, or when a bad decimal number is parsed.
     */
    private void parseExpression(String expr) throws Exception {
        /* Parse the expression into a stack of integers and a stack of 
        characters. */
        for (int i = 0; i < expr.length(); ) {
            String currentNum = "";
            
            // Is the current character the first digit of a number?
            char currentChar = expr.charAt(i);
            if (currentChar >= '0' && currentChar <= '9') {
                /* A number might contain multiple digits. 
                Collect all digits in the current number. */
                while ((i < expr.length() && currentChar >= '0' 
                        && currentChar <= '9')
                        ||
                        (i < expr.length() && currentChar == '.')) {
                    currentNum += currentChar;
                    i++; 
                    
                    /* If this isn't the last character in the string, get the
                    next character. */                                     
                    if (i < expr.length()) {
                        currentChar = expr.charAt(i);
                    }                  
                }
                
                try {
                    VALUES.push(new BigDecimal(currentNum).stripTrailingZeros());
                }
                
                catch (NumberFormatException e) {
                    throw new NumberFormatException(e.getMessage());
                }
            }
            
            // Push left parentheses to the operator stack
            else if (currentChar == '(') {
                /* Insert a multiplication operator between numbers and left
                parentheses so subexpressions such as 2(2+3) are evaluated
                properly. */
                if (i > 0) {
                    char previousChar = expr.charAt(i - 1);
                    if (previousChar >= '0' && previousChar <= '9') {
                        OPERATORS.push(ValidOperators.MULTIPLICATION.charAt(0));
                    }
                }
                
                OPERATORS.push(currentChar);
                i++;
            }
            
            /* If the current character is a right parenthesis, solve the
            subexpression it contains. */
            else if (currentChar == ')') {  
                while (OPERATORS.peek() != '(') {                   
                    evaluateSubexpression();
                }
                
                OPERATORS.pop();
                
                /* Insert a multiplication operator between numbers and right
                parentheses so subexpressions such as (2+3)2 are evaluated
                properly. */
                if (i < expr.length() - 1) {
                    char nextChar = expr.charAt(i + 1);
                    if (nextChar >= '0' && nextChar <= '9') {
                        OPERATORS.push(ValidOperators.MULTIPLICATION.charAt(0));
                    }
                }
                
                i++;
            }
            
            /* If the current character is an operator, evaluate subexpressions
            while the operator stack isn't empty, and while the operator on top
            of the stack has equal or higher precedence than the current
            operator. */
            else if (ValidOperators.isOperator(Character.toString(currentChar))) {
                while (!OPERATORS.empty() 
                        && checkPrecedence(OPERATORS.peek(), currentChar)) {
                    evaluateSubexpression();
                }
                
                OPERATORS.push(currentChar);
                i++;
            }
        }
    }
    
    /**
     * Evaluates a subexpression containing two values and an operator.
     * 
     * @throws Exception On attempts to divide a number by 0.
     */
    private void evaluateSubexpression() throws Exception {
        char operator = OPERATORS.pop();

        // Get the two operands in the correct order
        BigDecimal b = VALUES.pop();
        BigDecimal a = VALUES.pop();

            BigDecimal result = executeOperation(operator, a, b);
            VALUES.push(result);             
    }
    
    /**
     * Executes a single arithmetic operation.
     * 
     * @param operator One of the four arithmetic operators: +, -, *, or /.
     * @param a The operation's first operand.
     * @param b The operation's second operand.
     * @return The result of the operation.
     * @throws Exception On attempts to divide a number by 0.
     */ 
    private BigDecimal executeOperation(char operator, BigDecimal a, BigDecimal b) 
        throws Exception {
            switch (Character.toString(operator)) {
                case ValidOperators.DIVISION:
                    if (b.equals(0)) {
                        throw new ArithmeticException("DIVIDE BY 0 ERROR");
                    }                   
                    return a.divide(b, 5, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
                case ValidOperators.MULTIPLICATION:
                    return a.multiply(b);
                case ValidOperators.ADDITION:
                    return a.add(b);
                case ValidOperators.SUBTRACTION:
                    return a.subtract(b);
                default:
                    return new BigDecimal(0);
            }
    }
    
    /**
     * Determines the precedence of 2 operators.
     * 
     * @param aOperator1 The operator on the top of the operators stack.
     * @param aOperator2 The current operator.
     * @return "true" if `operator1` has precedence over `operator2`, false
     * otherwise.
     */
    private boolean checkPrecedence(char aOperator1, char aOperator2) {       
        String operator1 = Character.toString(aOperator1);
        String operator2 = Character.toString(aOperator2);
        
        if (operator1.equals("(") || operator1.equals(")")) {
            return false;
        }
        
        // Does operator1 have precedence over operator2?
        if ((operator1.equals(ValidOperators.ADDITION) 
                || operator1.equals(ValidOperators.SUBTRACTION))
                && 
                (operator2.equals(ValidOperators.MULTIPLICATION) 
                || operator2.equals(ValidOperators.DIVISION))) {
            return false;
        }
        
        else {
            return true;
        } 
    }
    
}
