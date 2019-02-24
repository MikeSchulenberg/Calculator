/*
 * Copyright (C) 2018, 2019 Mike Schulenberg
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

package com.mikeschulenbergdev.calculator.view.gui;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/**
 * This class adds additional JButton behavior.
 * 
 * @author Mike Schulenberg
 * @version 1.0.1
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
        setPreferredSize(new Dimension(53, 30));
    }
    
}
