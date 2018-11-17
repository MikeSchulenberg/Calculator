// TODO: add keyboard input
// TODO: experiment increasing the font size on buttons and display
// TODO: bug - entering an subexpression containing parentheses with nothing between them, i.e. (), isn't handled properly
// TODO: bug - if an expression evaluates to 0, new input is prepended with that 0

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *  This class builds a GUI for a 4-function calculator.
 * 
 * @author Mike Schulenberg
 */
public class Calculator extends JFrame {
    private StringBuilder sb;
    private CalcHandler calcHandler;
    
    private JPanel mainPanel;
    private JLabel display;

    private final String DELETE_SYMBOL = "\u00AB"; // « symbol
    
    public Calculator() {
        sb = new StringBuilder();
        calcHandler = new CalcHandler();
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
        
        setVisible(true);
    }
    
    /**
     * Sets default attributes for UI's main window.
     */
    private void prepFrame() {
        setTitle("Calculator");
        final int FRAME_WIDTH = 250, FRAME_HEIGHT = 280;
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
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
        mainPanel.add(display, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow2() {      
        JButton newButton;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        newButton = new JButton("C");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        newButton = new JButton("(");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        newButton = new JButton(")");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 1;      
        newButton = new JButton(ValidOperators.DIVISION);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow3() {      
        JButton newButton;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        newButton = new JButton("7");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        newButton = new JButton("8");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        newButton = new JButton("9");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 2;
        newButton = new JButton(ValidOperators.MULTIPLICATION);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow4() {
        JButton newButton;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        newButton = new JButton("4");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        newButton = new JButton("5");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        newButton = new JButton("6");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        newButton = new JButton(ValidOperators.ADDITION);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow5() {
        JButton newButton;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        newButton = new JButton("1");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        newButton = new JButton("2");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 4;
        newButton = new JButton("3");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 4;
        newButton = new JButton(ValidOperators.SUBTRACTION);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow6() {
        JButton newButton;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        newButton = new JButton("0");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        newButton = new JButton(".");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 5;
        newButton = new JButton(DELETE_SYMBOL);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 5;
        newButton = new JButton("=");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
    }
    
    /**
     * ActionListener for all buttons.
     */
    private class BListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String clickedButtonText = e.getActionCommand();
            switch (clickedButtonText) {
                case "C" :
                    clearExpression();
                    break;
                case DELETE_SYMBOL :
                    deleteLastChar();
                    break;
                case "=" :
                    evaluateExpression();
                    break;
                default :
                    updateExpression(clickedButtonText);
            }
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
                String result = calcHandler.calculate(sb.toString());
                printResult(result);
            }
        }    
        
        catch (Exception e) {
            printMessage(e.getMessage());
        }
    }
    
    /**
     * Updates the current expression with user input.
     * 
     * @param newChar The character, as a String, to be appended to the
     * expression.
     */
    private void updateExpression(String newChar) {
        /* If a decimal point is the first character in a new expression,
        prepend the expression with a 0. */
        if (sb.length() == 0 && newChar.equals(".")) {
            sb.append("0");
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
        display.setText(newText);
    }
}
