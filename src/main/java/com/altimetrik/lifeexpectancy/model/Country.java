package com.altimetrik.lifeexpectancy.model;

import java.util.List;

public class Country {
	List<String> countries;

	public Country() {
		
	}
	
	public Country(List<String> countries) {
		super();
		this.countries = countries;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}
	
}
