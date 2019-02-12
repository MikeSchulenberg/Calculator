package com.mikeschulenbergdev.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.mikeschulenbergdev.calculator.view.View;

@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new SpringApplicationBuilder(CalculatorApplication.class)
					.headless(false)
					.run(args);
	
		View view = context.getBean(View.class);	
	}

}

