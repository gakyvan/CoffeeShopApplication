package com.coffeeShop.clientApi;

import org.springframework.web.client.RestTemplate;

import com.coffeeShop.model.Product;

public class CoffeeShopApiClient {

	public void getProduct() {
		RestTemplate restTemplate=new RestTemplate();
		Product product=restTemplate.getForObject("http://localhost:8080/api/product/1", Product.class);
		printData(product);
	}
	
	public void printData(Product product) {
		System.out.println(product);
	}
}
