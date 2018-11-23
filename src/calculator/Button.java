/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/**
 * This class adds additional default behavior to the calculator's JButtons.
 * 
 * @author Mike Schulenberg
 */
public class Button extends JButton{
    public Button(String text) {
        super(text);
        initButton();
    }
    
    /**
     * Initializes the new button behavior.
     */
    private void initButton() {
        setFont(new Font("Arial", Font.PLAIN, 18));
        setFocusPainted(false);
        setPreferredSize(new Dimension(47, 30));
    }
}
