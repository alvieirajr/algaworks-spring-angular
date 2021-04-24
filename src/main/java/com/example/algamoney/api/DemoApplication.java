package com.example.algamoney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.algamoney.api.config.property.AlgaMoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(AlgaMoneyApiProperty.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

} 
