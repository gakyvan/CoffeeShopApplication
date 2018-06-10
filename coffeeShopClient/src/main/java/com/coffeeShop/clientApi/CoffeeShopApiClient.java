package com.coffeeShop.clientApi;

import org.springframework.web.client.RestTemplate;

import com.coffeeShop.model.Product;

public class CoffeeShopApiClient {

	public Product getProduct(String productID) {
		RestTemplate restTemplate=new RestTemplate();
		Product product=restTemplate.getForObject("http://localhost:8080/api/product/"+productID, Product.class);
		return product;
	}
	
}
