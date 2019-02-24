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

package com.mikeschulenbergdev.calculator.input;

import static com.mikeschulenbergdev.calculator.view.View.BACKSPACE_SYMBOL;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mikeschulenbergdev.calculator.core.ValidOperators;
import com.mikeschulenbergdev.calculator.view.View;

/**
 * This class sets up all key bindings used by the program.
 * 
 * @author Mike Schulenberg
 * @version 1.0.1
 */

@Component
public class KeyBindings {
	
    private final JComponent MAIN_PANEL;
    private final InputHandler INPUT_HANDLER;
    
    @Autowired
    public KeyBindings(View view, InputHandler inputHandler) {
    	INPUT_HANDLER = inputHandler;
        MAIN_PANEL = view.getMainPanel();
        
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
            MAIN_PANEL.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke((char) ('0' + i)), "key" + i);
            MAIN_PANEL.getActionMap()
                    .put("key" + i, new KeyAction(String.valueOf(i)));
        }  
    }
    
    /**
     * Preps the operator keys.
     */
    private void prepOperatorKeys() {
        MAIN_PANEL.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('/'), "division");
        MAIN_PANEL.getActionMap()
                    .put("division", new KeyAction(ValidOperators.DIVISION));
        
        MAIN_PANEL.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('*'), "multiplication");
        MAIN_PANEL.getActionMap()
                    .put("multiplication", new KeyAction(ValidOperators.MULTIPLICATION));
        
        MAIN_PANEL.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('+'), "addition");
        MAIN_PANEL.getActionMap()
                    .put("addition", new KeyAction(ValidOperators.ADDITION));
        
        MAIN_PANEL.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('-'), "subtraction");
        MAIN_PANEL.getActionMap()
                    .put("subtraction", new KeyAction(ValidOperators.SUBTRACTION));
    }
    
    /**
     * Preps the remaining keys.
     */
    private void prepSupplementalKeys() {
        MAIN_PANEL.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "clear");
        MAIN_PANEL.getActionMap()
                    .put("clear", new KeyAction("C"));
        
        MAIN_PANEL.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('('), "rightParen");
        MAIN_PANEL.getActionMap()
                    .put("rightParen", new KeyAction("("));
        
        MAIN_PANEL.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke(')'), "leftParen");
        MAIN_PANEL.getActionMap()
                    .put("leftParen", new KeyAction(")"));
        
        MAIN_PANEL.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('.'), "decimal");
        MAIN_PANEL.getActionMap()
                    .put("decimal", new KeyAction("."));
        
        MAIN_PANEL.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "backSpace");
        MAIN_PANEL.getActionMap()
                    .put("backSpace", new KeyAction(BACKSPACE_SYMBOL));
        
        MAIN_PANEL.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke('='), "equals");
        MAIN_PANEL.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                    .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "equals");
        MAIN_PANEL.getActionMap()
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
            INPUT_HANDLER.processInput(keyPressed);
        }
    }
    
}
