/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * This class sets up key bindings for the calculator.
 * 
 * @author Mike Schulenberg
 */
public class Keys {
    Calculator calculator;
    JComponent component;
    
    public Keys(Calculator calculator) {
        this.calculator = calculator;
        component = calculator.getMainPanel();
        prepKeyBindings();
    }
    
    private void prepKeyBindings() {
       // Prep number keys
        int numDigits = 10;
        for (int i = 0; i < numDigits; i++) {
            component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke(String.valueOf(i)), "pressed" + i);
            component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke("NUMPAD" + i), "pressed" + i);
            component.getActionMap()
                    .put("pressed" + i, new KeyAction(String.valueOf(i)));
        }       
    }
    
    private class KeyAction extends AbstractAction {
        String keyPressed;
        
        KeyAction(String keyPressed) {
            this.keyPressed = keyPressed;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            calculator.processInput(keyPressed);
        }
    }
}
