package com.coffeeShop.clientApi;

public class CoffeeShopApiTest {
	public static void main(String... args) {
		CoffeeShopApiClient cac = new CoffeeShopApiClient();
		System.out.println(cac.getProduct("1"));
	}
}
