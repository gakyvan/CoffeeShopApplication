package edu.mum.coffee.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private int id;
	@NotEmpty
	private String productName;
	@NotEmpty
	private String description;
	@Min(value = 1)
	@NotNull
	private double price;
	@NotNull
	@Enumerated(EnumType.STRING)
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
