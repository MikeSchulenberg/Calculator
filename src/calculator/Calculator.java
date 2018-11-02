/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Mike Schulenberg
 */
public class Calculator extends JFrame {
    private final int WIDTH = 250;
    private final int HEIGHT = 350;
    private JPanel mainPanel;
    
    public Calculator() {
        prepUI();
    }
    
    public static void main(String[] args) {
        new Calculator();
    }
    
    private void prepUI() {
        prepFrame();
        prepComponents();
        
        setVisible(true);
    }
    
    private void prepFrame() {
        setTitle("Calculator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void prepComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        prepRow1(gbc);
        prepRow2(gbc);
        prepRow3(gbc);
        prepRow4(gbc);
        prepRow5(gbc);
        prepRow6(gbc);
        
        add(mainPanel);
    }
    
    private void prepRow1(GridBagConstraints gbc) {
        
    }
    
    private void prepRow2(GridBagConstraints gbc) {      
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton bClear = new JButton("C");
        mainPanel.add(bClear, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton bLeftParen = new JButton("(");
        mainPanel.add(bLeftParen, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        JButton bRightParen = new JButton(")");
        mainPanel.add(bRightParen, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 2;      
        JButton bDivide = new JButton("\u00F7"); // ÷ sign
        mainPanel.add(bDivide, gbc); 
    }
    
    private void prepRow3(GridBagConstraints gbc) {      
        gbc.gridx = 0;
        gbc.gridy = 3;
        JButton b7 = new JButton("7");
        mainPanel.add(b7, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton b8 = new JButton("8");
        mainPanel.add(b8, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        JButton b9 = new JButton("9");
        mainPanel.add(b9, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        JButton bMultiply = new JButton("\u00D7"); // × sign
        mainPanel.add(bMultiply, gbc);
    }
    
    private void prepRow4(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 4;
        JButton b4 = new JButton("4");
        mainPanel.add(b4, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        JButton b5 = new JButton("5");
        mainPanel.add(b5, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 4;
        JButton b6 = new JButton("6");
        mainPanel.add(b6, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 4;
        JButton bAdd = new JButton("+");
        mainPanel.add(bAdd, gbc);
    }
    
    private void prepRow5(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton b1 = new JButton("1");
        mainPanel.add(b1, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        JButton b2 = new JButton("2");
        mainPanel.add(b2, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 5;
        JButton b3 = new JButton("3");
        mainPanel.add(b3, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 5;
        JButton bSubtract = new JButton("-");
        mainPanel.add(bSubtract, gbc);
    }
    
    private void prepRow6(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 6;
        JButton b0 = new JButton("0");
        mainPanel.add(b0, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        JButton bDecimal = new JButton(".");
        mainPanel.add(bDecimal, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 6;
        JButton bBack = new JButton("\u00AB"); // « symbol
        mainPanel.add(bBack, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 6;
        JButton bEquals = new JButton("=");
        mainPanel.add(bEquals, gbc);
    }
}
