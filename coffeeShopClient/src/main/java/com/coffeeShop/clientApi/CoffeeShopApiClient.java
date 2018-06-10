package com.coffeeShop.clientApi;

import org.springframework.web.client.RestTemplate;

import com.coffeeShop.model.Product;

public class CoffeeShopApiClient {

	public void getProduct() {
		RestTemplate restTemplate=new RestTemplate();
		String s=restTemplate.getForObject("http://localhost:8080/api/product/1", String.class);
		System.out.println(s);
		//printData(product);
	}
	
	public void printData(Product product) {
		System.out.println(product);
	}
}
