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
public class UI {
    private final int WIDTH = 250;
    private final int HEIGHT = 350;
    
    JFrame window = new JFrame();
    
    public UI() {
        prepUI();
    }
    
    private void prepUI() {
        window.setTitle("Calculator");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }   
}
