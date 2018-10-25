/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javax.swing.JFrame;

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
        setTitle("Calculator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }  
}
