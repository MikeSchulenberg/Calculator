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
    JComponent component;
    
    public Keys(JComponent component) {
        this.component = component;
        prepKeyBindings();
    }
    
    private void prepKeyBindings() {
        component.getInputMap().put(KeyStroke.getKeyStroke("1"), "pressed1");
        component.getInputMap().put(KeyStroke.getKeyStroke("NUMPAD1"), "pressed1");
        component.getActionMap().put("pressed1", new KeyAction("1"));
    }
    
    private class KeyAction extends AbstractAction {
        String keyPressed;
        
        KeyAction(String keyPressed) {
            this.keyPressed = keyPressed;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(keyPressed);
        }
    }
}
