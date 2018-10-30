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
        // create a master container to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        
        // create buttons for the 1st row
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");
        JButton bDivide = new JButton("\u00F7");
        
        // create buttons for the 2nd row
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton bMultiply = new JButton("\u00D7");
        
        // create buttons for the 3rd row
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton bAdd = new JButton("+");
        
        // create buttons for the 4th row
        JButton b0 = new JButton("0");
        JButton bDecimal = new JButton(".");
        JButton bEquals = new JButton("=");
        JButton bSubtract = new JButton("-");
        
        // create panels to hold the 1st row of buttons
        JPanel b7Panel = new JPanel();
        JPanel b8Panel = new JPanel();
        JPanel b9Panel = new JPanel();
        JPanel bDividePanel = new JPanel();
        
        // create panels to hold the 2nd row of buttons
        JPanel b4Panel = new JPanel();
        JPanel b5Panel = new JPanel();
        JPanel b6Panel = new JPanel();
        JPanel bMultiplyPanel = new JPanel();
        
        // create panels to hold the 3rd row of buttons
        JPanel b1Panel = new JPanel();
        JPanel b2Panel = new JPanel();
        JPanel b3Panel = new JPanel();
        JPanel bAddPanel = new JPanel();
        
        // create panels to hold the 4th row of buttons
        JPanel b0Panel = new JPanel();
        JPanel bDecimalPanel = new JPanel();
        JPanel bEqualsPanel = new JPanel();
        JPanel bSubtractPanel = new JPanel();
        
        // add the 1st row of buttons to their panels
        b7Panel.add(b7);
        b8Panel.add(b8);
        b9Panel.add(b9);
        bDividePanel.add(bDivide);
        
        // add the 2nd row of buttons to their panels
        b4Panel.add(b4);
        b5Panel.add(b5);
        b6Panel.add(b6);
        bMultiplyPanel.add(bMultiply);
        
        // add the 3rd row of buttons to their panels
        b1Panel.add(b1);
        b2Panel.add(b2);
        b3Panel.add(b3);
        bAddPanel.add(bAdd);
        
        // add the 4th row of buttons to their panels
        b0Panel.add(b0);
        bDecimalPanel.add(bDecimal);
        bEqualsPanel.add(bEquals);
        bSubtractPanel.add(bSubtract);
        
        // add the completed 1st row to the master container
        buttonPanel.add(b7Panel);
        buttonPanel.add(b8Panel);
        buttonPanel.add(b9Panel);
        buttonPanel.add(bDividePanel);
        
        // add the completed 2nd row to the master container
        buttonPanel.add(b4Panel);
        buttonPanel.add(b5Panel);
        buttonPanel.add(b6Panel);
        buttonPanel.add(bMultiplyPanel);
        
        // add the completed 3rd row to the master container
        buttonPanel.add(b1Panel);
        buttonPanel.add(b2Panel);
        buttonPanel.add(b3Panel);
        buttonPanel.add(bAddPanel);
        
        // add the completed 4th row to the master container
        buttonPanel.add(b0Panel);
        buttonPanel.add(bDecimalPanel);
        buttonPanel.add(bEqualsPanel);
        buttonPanel.add(bSubtractPanel);
        
        add(buttonPanel);
    }
}
