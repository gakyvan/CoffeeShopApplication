package edu.mum.coffee.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Address {

	@Id
	@GeneratedValue
	private int id;
	@NotEmpty
	@Size(min = 2, max = 40)
	private String city;
	@NotEmpty
	@Size(min = 2, max = 40)
	private String state;
	@NotEmpty
	@Size(min = 2, max = 40)
	private String country;
	@NotNull
	@Pattern(regexp="^[0-9]{5}$")
	private String zipcode;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	} 

	public String getZipcode() {
		return zipcode;  
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
 
	@Override
	public String toString() {
		return zipcode+ " - "+ city+ " / "+ state +" / "+country;
	}
 
}
