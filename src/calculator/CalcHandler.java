/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

import java.util.Stack;

/**
 * This class provides all calculation functions for Calculator.java.
 * 
 * @author Mike Schulenberg
 */
public class CalcHandler{
    private final Stack<Integer> VALUES = new Stack<>();
    private final Stack<Character> OPERATORS = new Stack<>();
    
    /**
     * Evaluates an arithmetic expression according to operator precedence.
     * 
     * @param expr The expression to be evaluated.
     */
    public int calculate(String expr) {       
        final String VALID_OPERATORS = "\\+|-|\\*|/";
        
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
                
                VALUES.push(Integer.parseInt(currentNum));
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
            else if (Character.toString(currentChar).matches(VALID_OPERATORS)) {
                while (!OPERATORS.empty() 
                        && checkPrecedence(OPERATORS.peek(), currentChar)) {
                            evaluateSubexpression();
                }
                
                OPERATORS.push(currentChar);
                i++;
            }
        }
        
        while (!OPERATORS.empty()) {
            evaluateSubexpression();
        }
        
        return VALUES.pop();
    }
    
    /**
     * Evaluates a subexpression containing two values and an operator.
     */
    private void evaluateSubexpression() {
        char operator = OPERATORS.pop();

        // Get the two operands in the correct order
        int b = VALUES.pop();
        int a = VALUES.pop();

        try {
            int result = executeOperation(operator, a, b);
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
    private int executeOperation(char operator, int a, int b) 
        throws Exception {
            switch (operator) {
                case '+':
                    return a + b;
                case '-':
                    return a - b;
                case '*':
                    return a * b;
                case '/':
                    if (b == 0) {
                        throw new Exception("DIVIDE BY 0 ERROR");
                    }                   
                    return a / b;
                default:
                    return 0;
            }
    }
    
    /**
     * Determines the precedence of 2 operators.
     * 
     * @param operator1 The operator on the top of the operators stack.
     * @param operator2 The current operator.
     * @return "true" if `operator1` has precedence over `operator2`, false
     * otherwise.
     */
    private boolean checkPrecedence(char operator1, char operator2) {       
        if (operator1 == '(' || operator1 == ')') {
            return false;
        }
        
        if ((operator1 == '+' || operator1 == '-')
                && (operator2 == '*' || operator2 == '/')) {
            return false;
        }
        
        else {
            return true;
        } 
    }
    
    public String test(String str) {
        System.out.println(str);
        return "Polo";
    }
}
