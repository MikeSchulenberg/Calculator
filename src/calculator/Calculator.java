// TODO: prevent division operations from dropping remainders
// TODO: handle 'divide by 0' error
// TODO: streamline button prep
// TODO: add keyboard input

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
    private JPanel mainPanel;
    private JLabel display;
    private StringBuilder sb;
    private CalcHandler calcHandler;
    
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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton bClear = new JButton("C");
        bClear.addActionListener(new BListener());
        mainPanel.add(bClear, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton bLeftParen = new JButton("(");
        bLeftParen.addActionListener(new BListener());
        mainPanel.add(bLeftParen, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        JButton bRightParen = new JButton(")");
        bRightParen.addActionListener(new BListener());
        mainPanel.add(bRightParen, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 1;      
        JButton bDivide = new JButton(ValidOperators.DIVISION);
        bDivide.addActionListener(new BListener());
        mainPanel.add(bDivide, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow3() {      
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton b7 = new JButton("7");
        b7.addActionListener(new BListener());
        mainPanel.add(b7, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton b8 = new JButton("8");
        b8.addActionListener(new BListener());
        mainPanel.add(b8, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        JButton b9 = new JButton("9");
        b9.addActionListener(new BListener());
        mainPanel.add(b9, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 2;
        JButton bMultiply = new JButton(ValidOperators.MULTIPLICATION);
        bMultiply.addActionListener(new BListener());
        mainPanel.add(bMultiply, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow4() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        JButton b4 = new JButton("4");
        b4.addActionListener(new BListener());
        mainPanel.add(b4, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton b5 = new JButton("5");
        b5.addActionListener(new BListener());
        mainPanel.add(b5, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        JButton b6 = new JButton("6");
        b6.addActionListener(new BListener());
        mainPanel.add(b6, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        JButton bAdd = new JButton(ValidOperators.ADDITION);
        bAdd.addActionListener(new BListener());
        mainPanel.add(bAdd, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow5() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        JButton b1 = new JButton("1");
        b1.addActionListener(new BListener());
        mainPanel.add(b1, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        JButton b2 = new JButton("2");
        b2.addActionListener(new BListener());
        mainPanel.add(b2, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 4;
        JButton b3 = new JButton("3");
        b3.addActionListener(new BListener());
        mainPanel.add(b3, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 4;
        JButton bSubtract = new JButton(ValidOperators.SUBTRACTION);
        bSubtract.addActionListener(new BListener());
        mainPanel.add(bSubtract, gbc);
    }
    
    /**
     * Builds a row of buttons and adds it to the UI.
     */
    private void prepRow6() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton b0 = new JButton("0");
        b0.addActionListener(new BListener());
        mainPanel.add(b0, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        JButton bDecimal = new JButton(".");
        bDecimal.addActionListener(new BListener());
        mainPanel.add(bDecimal, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 5;
        JButton bBack = new JButton("\u00AB"); // « symbol
        bBack.addActionListener(new BListener());
        mainPanel.add(bBack, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 5;
        JButton bEquals = new JButton("=");
        bEquals.addActionListener(new BListener());
        mainPanel.add(bEquals, gbc);
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
                case "\u00AB" : // « symbol
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
        if (sb.length() > 0) {
            String result = calcHandler.calculate(sb.toString());
            printResult(result);
        }  
    }
    
    /**
     * Updates the current expression with user input.
     * 
     * @param newChar The character, as a String, to be appended to the
     * expression.
     */
    private void updateExpression(String newChar) {
       // Prevent the user from entering two operators in a row.
        if (sb.length() > 0) {
            char lastChar = sb.charAt(sb.length() - 1);
            /* If the last character in the StringBuilder instance is an
            operator, and the new String to be appended is also an operator,
            delete the last character from the StringBuilder so the new
            operator can take its place. */           
            if (ValidOperators.isOperator(Character.toString(lastChar))
                    && ValidOperators.isOperator(newChar))
            {
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
        sb.setLength(0);
        sb.append(result);
        display.setText(result);
    }
}
