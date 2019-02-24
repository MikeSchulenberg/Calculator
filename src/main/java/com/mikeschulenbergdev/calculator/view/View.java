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

package com.mikeschulenbergdev.calculator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.mikeschulenbergdev.calculator.core.ValidOperators;
import com.mikeschulenbergdev.calculator.input.InputHandler;
import com.mikeschulenbergdev.calculator.input.KeyBindings;
import com.mikeschulenbergdev.calculator.view.gui.Button;

/**
 *  This program is an arithmetic calculator that evaluates expressions 
 * according to the rules of operator precedence.
 * 
 * This class builds the GUI.
 * 
 * @author Mike Schulenberg
 * @version 1.0.1
 */

@Component
public class View extends JFrame {
	
    private final InputHandler INPUT_HANDLER;    
    
    private JPanel mainPanel;
    private JLabel display;
    private final ArrayList<Button> NUMBER_PAD;
    
    public static final String BACKSPACE_SYMBOL = "\u2190"; // char: ←
    
    @Autowired
    public View(@Lazy InputHandler inputHandler) {       
        NUMBER_PAD = new ArrayList<>();        
        prepUI();  
        INPUT_HANDLER = inputHandler;
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
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        newButton = new Button(")");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 3;
        gbc.gridy = 1;      
        newButton = new Button(ValidOperators.DIVISION);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        NUMBER_PAD.add(newButton);
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
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        newButton = new Button("8");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        newButton = new Button("9");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 3;
        gbc.gridy = 2;
        newButton = new Button(ValidOperators.MULTIPLICATION);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        NUMBER_PAD.add(newButton);
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
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        newButton = new Button("5");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        newButton = new Button("6");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        newButton = new Button(ValidOperators.ADDITION);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        NUMBER_PAD.add(newButton);
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
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        newButton = new Button("2");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 2;
        gbc.gridy = 4;
        newButton = new Button("3");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 3;
        gbc.gridy = 4;
        newButton = new Button(ValidOperators.SUBTRACTION);
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        NUMBER_PAD.add(newButton);
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
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        newButton = new Button(".");
        newButton.addActionListener(new BListener());
        mainPanel.add(newButton, gbc);
        NUMBER_PAD.add(newButton);
        
        gbc.gridx = 2;
        gbc.gridy = 5;
        newButton = new Button(BACKSPACE_SYMBOL);
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
     * Gets the main component for displaying text to the user.
     * 
     * @return The main display component.
     */
    public JLabel getDisplay() {
        return display;
    }
    
    /**
     * Gets the collection of buttons that allows the user to input
     * an arithmetic expression.
     * 
     * @return The calculator's number pad.
     */
    public ArrayList<Button> getNumberPad() {
        return NUMBER_PAD;
    }
    
    /**
     * ActionListener for all buttons.
     */
    private class BListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            INPUT_HANDLER.processInput(e.getActionCommand());            
        }
    }
    
}
