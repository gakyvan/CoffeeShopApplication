package com.coffeeShop.model;

public class Product {

	private int id;
	private String productName;
	private String description;
	private double price;
	private ProductType productType;
	private boolean enabled;

	public Product() {
		super(); // default constructor
	}

	public Product(String productName, String description, double price, ProductType productType, boolean enabled) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.productType = productType;
		this.enabled = enabled;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPriceToString() {
		return "$" + price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
