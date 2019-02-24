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

package com.mikeschulenbergdev.calculator.input;

import static com.mikeschulenbergdev.calculator.view.View.BACKSPACE_SYMBOL;

import java.util.ArrayList;

import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.mikeschulenbergdev.calculator.core.ExpressionEvaluator;
import com.mikeschulenbergdev.calculator.core.ValidOperators;
import com.mikeschulenbergdev.calculator.view.View;
import com.mikeschulenbergdev.calculator.view.gui.Button;

/**
 * This class manages the user input for the program.
 * 
 * @author Mike Schulenberg
 * @version 1.0.1
 */

@Component
public class InputHandler {
	
    private final StringBuilder SB;
    private final ExpressionEvaluator EVALUATOR; 
    
    private final JLabel DISPLAY;
    private final ArrayList<Button> NUMBER_PAD;
    
    /* When checking the preferred width of the display showing the current 
    expression against its actual width, sometimes we need to know if it's
    greater-than-or-equal-to. Other times we need to know if it's 
    greater-than. */
    private enum WidthCheckMode {
        GREATER_THAN_OR_EQUAL_TO, GREATER_THAN
    }
    
    @Autowired
    public InputHandler(ExpressionEvaluator evaluator, View view) {
        SB = new StringBuilder();
        EVALUATOR = evaluator;
        DISPLAY = view.getDisplay();
        NUMBER_PAD = view.getNumberPad();
    }
    
    /**
     * Processes JButton and keyboard input to determine what action the program
     * should take.
     * 
     * @param input Input from either a JButton or the keyboard.
     */
    public void processInput(String input) {
        switch (input) {
            case "C" :
                clearExpression();
                break;
            case BACKSPACE_SYMBOL :
                deleteLastChar();
                break;
            case "=" :
                evaluateExpression();
                break;
            default :
                updateExpression(input);
        }
    }
    
    /**
     * Zeroes out the text in the main display and clears the StringBuilder
     * instance storing the current expression.
     */
    private void clearExpression() {
        DISPLAY.setText("0");
        SB.setLength(0);
        
        enableNumberPad(true);
        DISPLAY.setToolTipText(null);
    }
    
    /**
     * Deletes the last character in the current expression and updates the
     * display.
     */
    private void deleteLastChar() {
        /* If deleting the only character in the expression, clear the 
        StringBuilder instance and reset the display. */
        if (SB.length() == 1) {
            clearExpression();
        }
        
        /* Otherwise, delete the last character in the StringBuilder if it
        is not empty. */
        else if (SB.length() > 1) {
            SB.deleteCharAt(SB.length() - 1);
            printExpression();
            
            /* If the current expression has room for additional 
            characters, make sure the number pad is enabled. */
            if (!isExpressionFull(WidthCheckMode.GREATER_THAN_OR_EQUAL_TO)) {
                enableNumberPad(true);
            }
            
            /* If the current expression does not overflow the main display,
            remove the tool tip that shows the complete expression. */
            if (!isExpressionFull(WidthCheckMode.GREATER_THAN)) {
                DISPLAY.setToolTipText(null);
            }
            
            /* Otherwise, update the tooltip with the modified expression. */
            else {
                DISPLAY.setToolTipText(SB.toString());
            }
        }
    }
    
    /**
     * Submits the current expression for evaluation to a result.
     */
    private void evaluateExpression() {
        try {
            if (SB.length() > 0) {
                String result = EVALUATOR.calculate(SB.toString());               
                
                /* Prevent new expressions from prepending a 0 after evaluating
                an expression that results in 0. */
                if (Double.parseDouble(result) != 0) {
                    printResult(result);
                    
                    /* If the current expression has room for additional 
                    characters, make sure the number pad is enabled. */
                    if (!isExpressionFull(WidthCheckMode.GREATER_THAN_OR_EQUAL_TO)) {
                        enableNumberPad(true);
                    }
                }
                
                else {
                    clearExpression();
                }
            }
        }    
        
        catch (Exception e) {
            String msg = e.getMessage();
            if (msg == null) {
                printMessage("SYNTAX ERROR");
            }
            
            else {
                printMessage(msg);
            } 
        }
    }
    
    /**
     * Updates the current expression with user input.
     * 
     * @param newChar The character, as a String, to be appended to the
     * expression.
     */
    private void updateExpression(String newChar) {
        parseNewChar(newChar);
        
        SB.append(newChar);
        printExpression();
        
        /* If the current expression has reached its maxiumum allowed length,
        disable the number pad. */
        if (isExpressionFull(WidthCheckMode.GREATER_THAN_OR_EQUAL_TO)) {
            enableNumberPad(false);
        }
    }

    /**
     * Determines if the current expression needs to be modified before 
     * appending it with a new character, then makes those modifications.
     * 
     * @param newChar The character, as a String, to be parsed.
     */
    private void parseNewChar(String newChar) {
        /* If the first character in a new expression if a decimal point or an
        operator, prepend the expression with a 0. */
        if (SB.length() == 0) {
            if (newChar.equals(".") || ValidOperators.isOperator(newChar)) {
                    SB.append("0");
            }
        }

        // Prevent the user from entering two operators in a row.
        else if (SB.length() > 0) {
            String lastChar = Character.toString(SB.charAt(SB.length() - 1));
            
            /* If the last character in the StringBuilder instance is an
            operator, and the new String to be appended is also an operator,
            delete the last character from the StringBuilder so the new
            operator can take its place. */           
            if (ValidOperators.isOperator(lastChar)
                    && ValidOperators.isOperator(newChar))
            {
                deleteLastChar();
            }
            
            /* If a decimal point is the first character in a new term, prepend
            the new term with a 0. */
            else if (ValidOperators.isOperator(lastChar)
                    && newChar.equals(".")) {
                SB.append("0");
            }
            
            // Prevent consecutive decimal points.
            else if (lastChar.equals(".") && newChar.equals(".")) {
                deleteLastChar();
            }
            
            // Prevent empty parenthetical subexpressions.
            else if (lastChar.equals("(") && newChar.equals(")")) {
                SB.append("1");
            }
        }  
    }
    
    /**
     * Determines if the current expression has reached its maximum allowed
     * width and should be prevented from having new characters appended to it.
     * 
     * @param checkMode An enum that indicates whether true should be returned
     * if the main display's preferred width is greater-than-or-equal-to its
     * actual width, or if the main display's preferred width is greater-than
     * its actual width.
     * @return True if the current expression has reached its maximum allowed
     * width; false otherwise.
     */
    private boolean isExpressionFull(WidthCheckMode checkMode) {
        int preferredWidth = DISPLAY.getUI().getPreferredSize(DISPLAY).width;
        int preferredWidthIncrement = 10;
        int actualWidth = DISPLAY.getWidth();
        
        if (checkMode == WidthCheckMode.GREATER_THAN_OR_EQUAL_TO) {
            return preferredWidth > actualWidth - preferredWidthIncrement;
        }
       
        else {
            return preferredWidth > actualWidth;
        }
    }
    
    /**
     * Toggles the enabled/disabled state of the number pad.
     * 
     * @param setEnable True if the number pad should be enabled; false if it
     * should be disabled.
     */
    private void enableNumberPad(boolean setEnable) {
        boolean alreadyEnabled = NUMBER_PAD.get(0).isEnabled();
        
        if (setEnable && !alreadyEnabled) {
            NUMBER_PAD.forEach((current) -> {
                current.setEnabled(true);
            });
        }
        
        else if (!setEnable && alreadyEnabled) {
            NUMBER_PAD.forEach((current) -> {
                current.setEnabled(false);
            });
        }
    }
    
    /**
     * Prints the current expression to the main display.
     */
    private void printExpression() {
        DISPLAY.setText(SB.toString());
    }
    
    /**
     * Prints the result of a calculation to the main display.
     * 
     * @param result The result of a calculation.
     */
    private void printResult(String result) {
        updateDisplay(result);
        SB.append(result);
        
        /* If the result overflows the width of the main display, enable a tool
        tip that shows the entire result. */
        if (isExpressionFull(WidthCheckMode.GREATER_THAN)) {
            DISPLAY.setToolTipText(result);
        }
        
        else {
            DISPLAY.setToolTipText(null);
        }
    }
    
    /**
     * Prints text, such as error messages, to the main display.
     * 
     * @param msg The text to print to the display.
     */
    private void printMessage(String msg) {
        updateDisplay(msg);
        System.out.println(msg);
    }
    
    /**
     * Helper function that clears the current String in the StringBuilder
     * instance and updates the text in the main display.
     * 
     * @param newText The text to show in the display.
     */
    private void updateDisplay(String newText) {
        SB.setLength(0);       
        DISPLAY.setText(newText.toUpperCase());
    }
    
}
