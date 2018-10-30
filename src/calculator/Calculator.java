/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;

/**
 *
 * @author Mike Schulenberg
 */
public class Calculator extends JFrame {
    private final int WIDTH = 250;
    private final int HEIGHT = 350;
    
    public Calculator() {
        prepUI();
    }
    
    public static void main(String[] args) {
        new Calculator();
    }
    
    private void prepUI() {
        prepFrame();
        prepButtons();
        
        setVisible(true);
    }
    
    private void prepFrame() {
        setTitle("Calculator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void prepButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");
        JButton bDivide = new JButton("\u00F7");
        
        JPanel b7Panel = new JPanel();
        JPanel b8Panel = new JPanel();
        JPanel b9Panel = new JPanel();
        JPanel bDividePanel = new JPanel();
        
        b7Panel.add(b7);
        b8Panel.add(b8);
        b9Panel.add(b9);
        bDividePanel.add(bDivide);
        
        buttonPanel.add(b7Panel);
        buttonPanel.add(b8Panel);
        buttonPanel.add(b9Panel);
        buttonPanel.add(bDividePanel);
        
        add(buttonPanel);
    }
}
