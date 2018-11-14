/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

import java.text.DecimalFormat;
import java.util.Stack;

/**
 * This class provides all calculation functions for Calculator.java.
 * 
 * @author Mike Schulenberg
 */
public class CalcHandler{
    private final Stack<Double> VALUES = new Stack<>();
    private final Stack<Character> OPERATORS = new Stack<>();
    
    /**
     * Evaluates an arithmetic expression according to operator precedence.
     * 
     * @param expr The expression to be evaluated.
     * @return The result the expression evaluates to.
     */
    public String calculate(String expr) {              
        try {
            parseExpression(expr);
        }
        
        catch (Exception e) {
            return "SYNTAX ERROR";
        }
        
        while (!OPERATORS.empty()) {
            evaluateSubexpression();
        }
        
        double result = VALUES.pop();
        DecimalFormat df = new DecimalFormat("0.###");
        
        return df.format(result);
    }
    
    private void parseExpression(String expr) {
        /* Parse the expression into a stack of integers and a stack of 
        characters. */
        for (int i = 0; i < expr.length(); ) {
            String currentNum = "";
            
            // Is the current character the first digit of a number?
            char currentChar = expr.charAt(i);
            if (currentChar >= '0' && currentChar <= '9') {
                /* A number might contain multiple digits. 
                Collect all digits in the current number. */
                while (i < expr.length() && currentChar >= '0' 
                        && currentChar <= '9') {
                    currentNum += currentChar;
                    i++; 
                    
                    /* If this isn't the last character in the string, get the
                    next character. */                                     
                    if (i < expr.length()) {
                        currentChar = expr.charAt(i);
                    }                  
                }
                
                VALUES.push(Double.parseDouble(currentNum));
            }
            
            // Push left parentheses to the operator stack
            else if (currentChar == '(') {
                OPERATORS.push(expr.charAt(i));
                i++;
            }
            
            /* If the current character is a right parenthesis, solve the
            subexpression it contains. */
            else if (currentChar == ')') {
                while (OPERATORS.peek() != '(') {
                    
                    evaluateSubexpression();
                }
                
                OPERATORS.pop();
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
     */
    private void evaluateSubexpression() {
        char operator = OPERATORS.pop();

        // Get the two operands in the correct order
        double b = VALUES.pop();
        double a = VALUES.pop();

        try {
            double result = executeOperation(operator, a, b);
            VALUES.push(result);
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }               
    }
    
    /**
     * Executes a single arithmetic operation.
     * 
     * @param operator One of the four arithmetic operators: +, -, *, or /.
     * @param a The operation's first operand.
     * @param b The operation's second operand.
     * @return The result of the operation.
     * @throws Exception 
     */ 
    private double executeOperation(char operator, double a, double b) 
        throws Exception {
            switch (Character.toString(operator)) {
                case ValidOperators.DIVISION:
                    if (b == 0) {
                        throw new Exception("DIVIDE BY 0 ERROR");
                    }                   
                    return a / b;
                case ValidOperators.MULTIPLICATION:
                    return a * b;
                case ValidOperators.ADDITION:
                    return a + b;
                case ValidOperators.SUBTRACTION:
                    return a - b;
                default:
                    return 0;
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
