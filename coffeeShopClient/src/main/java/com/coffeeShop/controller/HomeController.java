package com.coffeeShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coffeeShop.clientApi.CoffeeShopApiClient;

@Controller
public class HomeController {

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String getData(@PathVariable String id, Model model) {
		System.out.println("test");
		model.addAttribute("product", new CoffeeShopApiClient().getProduct(id));
		return "home";
	}
}
