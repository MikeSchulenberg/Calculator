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
    private final int WIDTH = 250;
    private final int HEIGHT = 280;
    private JPanel mainPanel;
    private JLabel display;
    private StringBuilder sb;
    
    public Calculator() {
        prepUI();
        sb = new StringBuilder();
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
        setSize(WIDTH, HEIGHT);
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
        JButton bDivide = new JButton("\u00F7"); // ÷ sign
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
        JButton bMultiply = new JButton("\u00D7"); // × sign
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
        JButton bAdd = new JButton("+");
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
        JButton bSubtract = new JButton("-");
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
    
    private class BListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String clickedButtonText = e.getActionCommand();
            switch (clickedButtonText) {
                case "C" :
                    clearExpression();
                    break;
                case "\u00AB" :
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
    
    private void clearExpression() {
        System.out.println("clicked C");
    }
    
    private void deleteLastChar() {
        System.out.println("clicked \u00AB");
    }
    
    private void evaluateExpression() {
        System.out.println("clicked =");
    }
    
    private void updateExpression(String s) {
        sb.append(s);
        display.setText(sb.toString());
    }
}
