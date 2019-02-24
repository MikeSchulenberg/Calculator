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

package com.mikeschulenbergdev.calculator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.mikeschulenbergdev.calculator.view.View;

/**
 * This class configures the application context.
 * 
 * @author Mike Schulenberg
 * @version 1.0.1
 */

@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new SpringApplicationBuilder(CalculatorApplication.class)
					.headless(false)
					.run(args);	
	}

}

