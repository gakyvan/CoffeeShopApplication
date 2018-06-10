package com.coffeeShop.confgi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.coffeeShop")
public class CoffeeShopClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeShopClientApplication.class, args);
	}
}
