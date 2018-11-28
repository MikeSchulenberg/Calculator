/*
 * Copyright (C) 2018 Mike Schulenberg
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

package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import static calculator.Calculator.BACKSPACE_SYMBOL;

/**
 * This class sets up all key bindings used by the program.
 * 
 * @author Mike Schulenberg
 * @version 1.0.0
 */
public class KeyBindings {
    Calculator calculator;
    JComponent component;
    InputHandler inputHandler;
    
    /**
     * Constructor requiring a Calculator object to receive commands and provide
     * a component used for key bindings. Also requires an InputHandler object
     * to pass input into.
     * 
     * @param calculator A Calculator object.
     * @param inputHandler An InputHandler object.
     */
    public KeyBindings(Calculator calculator, InputHandler inputHandler) {
        this.calculator = calculator;
        component = calculator.getMainPanel();
        this.inputHandler = inputHandler;
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
                    .put("backSpace", new KeyAction(BACKSPACE_SYMBOL));
        
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
            inputHandler.processInput(keyPressed);
        }
    }
}
