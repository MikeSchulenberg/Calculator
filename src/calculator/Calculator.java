/*
 * Copyright (C) 2018 Mike Schulenberg
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

package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *  This program is an arithmetic calculator that evaluates expressions 
 * according to the rules of operator precedence.
 * 
 * This class builds the GUI.
 * 
 * @author Mike Schulenberg
 * @version 1.0.0
 */
public class Calculator extends JFrame {
    private StringBuilder sb;
    private Evaluator evaluator;
    
    private JPanel mainPanel;
    private JLabel display;

    public static final String BACK_SPACE_SYMBOL = "\u2190"; // char: ←
    
    public Calculator() {
        sb = new StringBuilder();
        evaluator = new Evaluator();
        prepUI();
    }
    
    public static void main(String[] args) {
        new Calculator();      
    }
    
    /**
     * Builds and reveals the UI.
     */
    private void prepUI() {
        prepFrame();
        prepComponents();
        new Keys(this);
        
        setVisible(true);
    }
    
    /**
     * Sets default attributes for UI's main window.
     */
    private void prepFrame() {
        setTitle("Calculator");
        setSize(280, 296);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Builds all components contained by the main window.
     */
    private void prepComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        
        prepRow1();
        prepRow2();
        prepRow3();
        prepRow4();
        prepRow5();
        prepRow6();
        
        add(mainPanel);
    }
    
    /**
     * Builds the area where the calculator displays the expression to be
     * evaluated and its result.
     */
    private void prepRow1() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 25, 0);
        gbc.gridwidth = 4;
        gbc.ipady = 10;
        
        display = new JLabel("0");
        display.setOpaque(true);
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        display.setFont(new Font("Arial", Font.PLAIN, 18));
        
        /* Prevent extremely long expressions from resizing the main display
        and other UI components. */
        Dimension d = display.getPreferredSize();
        display.setPreferredSize(d);
        
        mainPanel.add(display, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow2() {      
        Button newButton;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        newButton = new Button("C");
        newButton.addActionListener((ActionListener) new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        newButton = new Button("(");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        newButton = new Button(")");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 1;      
        newButton = new Button(ValidOperators.DIVISION);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow3() {      
        Button newButton;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        newButton = new Button("7");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        newButton = new Button("8");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        newButton = new Button("9");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 2;
        newButton = new Button(ValidOperators.MULTIPLICATION);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow4() {
        Button newButton;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        newButton = new Button("4");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        newButton = new Button("5");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        newButton = new Button("6");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        newButton = new Button(ValidOperators.ADDITION);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow5() {
        Button newButton;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        newButton = new Button("1");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        newButton = new Button("2");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 4;
        newButton = new Button("3");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 4;
        newButton = new Button(ValidOperators.SUBTRACTION);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow6() {
        Button newButton;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        newButton = new Button("0");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        newButton = new Button(".");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 5;
        newButton = new Button(BACK_SPACE_SYMBOL);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 5;
        newButton = new Button("=");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
    }
    
    /**
     * Gets the main JPanel component.
     * 
     * @return The main JPanel component.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }
    
    /**
     * ActionListener for all buttons.
     */
    private class BListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            processInput(e.getActionCommand());            
        }
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
            case BACK_SPACE_SYMBOL :
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
        display.setText("0");
        sb.setLength(0);
    }
    
    /**
     * Deletes the last character in the current expression and updates the
     * display.
     */
    private void deleteLastChar() {
        /* If deleting the only character in the expression, clear the 
        StringBuilder instance and reset the display. */
        if (sb.length() == 1) {
            clearExpression();
        }
        
        /* Otherwise, delete the last character in the StringBuilder if it
        is not empty. */
        else if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
            printExpression();
        }
    }
    
    /**
     * Submits the current expression for evaluation to a result.
     */
    private void evaluateExpression() {
        try {
            if (sb.length() > 0) {
                String result = evaluator.calculate(sb.toString());               
                
                /* Prevent new expressions from prepending a 0 after evaluating
                an expression that results in 0. */
                if (Double.parseDouble(result) != 0) {
                    printResult(result);
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
        /* If the first character in a new expression if a decimal point or an
        operator, prepend the expression with a 0. */
        if (sb.length() == 0) {
            if (newChar.equals(".") || ValidOperators.isOperator(newChar)) {
                    sb.append("0");
            }
        }

        // Prevent the user from entering two operators in a row.
        else if (sb.length() > 0) {
            String lastChar = Character.toString(sb.charAt(sb.length() - 1));
            
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
                sb.append("0");
            }
            
            // Prevent consecutive decimal points.
            else if (lastChar.equals(".") && newChar.equals(".")) {
                deleteLastChar();
            }
            
            // Prevent empty parenthetical subexpressions.
            else if (lastChar.equals("(") && newChar.equals(")")) {
                sb.append("1");
            }
        }
        
        sb.append(newChar);
        printExpression();
    }
    
    /**
     * Prints the current expression to the main display.
     */
    private void printExpression() {
        display.setText(sb.toString());
    }
    
    /**
     * Prints the result of a calculation to the main display.
     * 
     * @param result The result of a calculation.
     */
    private void printResult(String result) {
        updateDisplay(result);
        sb.append(result);
    }
    
    /**
     * Prints text, such as error messages, to the main display.
     * 
     * @param msg The text to print to the display.
     */
    private void printMessage(String msg) {
        updateDisplay(msg);
    }
    
    /**
     * Helper function that clears the current String in the StringBuilder
     * instance and updates the text in the main display.
     * 
     * @param newText The text to show in the display.
     */
    private void updateDisplay(String newText) {
        sb.setLength(0);       
        display.setText(newText.toUpperCase());
    }
}
