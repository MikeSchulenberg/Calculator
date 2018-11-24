/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import static calculator.Calculator.BACK_SPACE_SYMBOL;

/**
 * This class sets up all key bindings used by the program.
 * 
 * @author Mike Schulenberg
 * @version 1.0.0
 */
public class Keys {
    Calculator calculator;
    JComponent component;
    
    /**
     * Constructor requiring a Calculator object to receive commands and provide
     * a component used for key bindings.
     * 
     * @param calculator A Calculator object.
     */
    public Keys(Calculator calculator) {
        this.calculator = calculator;
        component = calculator.getMainPanel();
        prepKeyBindings();
    }
    
    /**
     * Preps all key bindings.
     */
    private void prepKeyBindings() {       
        prepNumberKeys();
        prepOperatorKeys();
        prepSupplementalKeys();
    }
    
    /**
     * Preps the number keys.
     */
    private void prepNumberKeys() {
        int numDigits = 10;
        for (int i = 0; i < numDigits; i++) {
            component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke((char) ('0' + i)), "key" + i);
            component.getActionMap()
                    .put("key" + i, new KeyAction(String.valueOf(i)));
        }  
    }
    
    /**
     * Preps the operator keys.
     */
    private void prepOperatorKeys() {
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('/'), "division");
        component.getActionMap()
                    .put("division", new KeyAction(ValidOperators.DIVISION));
        
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('*'), "multiplication");
        component.getActionMap()
                    .put("multiplication", new KeyAction(ValidOperators.MULTIPLICATION));
        
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('+'), "addition");
        component.getActionMap()
                    .put("addition", new KeyAction(ValidOperators.ADDITION));
        
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('-'), "subtraction");
        component.getActionMap()
                    .put("subtraction", new KeyAction(ValidOperators.SUBTRACTION));
    }
    
    /**
     * Preps the remaining keys.
     */
    private void prepSupplementalKeys() {
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "clear");
        component.getActionMap()
                    .put("clear", new KeyAction("C"));
        
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('('), "rightParen");
        component.getActionMap()
                    .put("rightParen", new KeyAction("("));
        
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke(')'), "leftParen");
        component.getActionMap()
                    .put("leftParen", new KeyAction(")"));
        
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('.'), "decimal");
        component.getActionMap()
                    .put("decimal", new KeyAction("."));
        
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "backSpace");
        component.getActionMap()
                    .put("backSpace", new KeyAction(BACK_SPACE_SYMBOL));
        
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('='), "equals");
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "equals");
        component.getActionMap()
                    .put("equals", new KeyAction("="));
    }
    
    /**
     * Action that relays input to the calculator for processing.
     */
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
