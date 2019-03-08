package com.altimetrik.lifeexpectancy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LifeExpectancy {
	
	private String sex;
	private String country;
	@JsonUnwrapped
	@JsonProperty("dob")
	private String dob;
	@JsonUnwrapped
	@JsonProperty("total_life_expectancy")
	private Double totalLifeExpectancy;
	
	public LifeExpectancy() {
		
	}
	public LifeExpectancy(String sex, String country, String date, Double remainingLifeExpectancy) {
		super();
		this.sex = sex;
		this.country = country;
		this.dob = date;
		this.totalLifeExpectancy = remainingLifeExpectancy;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDate() {
		return dob;
	}
	public void setDate(String date) {
		this.dob = date;
	}
	public Double getRemainingLifeExpectancy() {
		return totalLifeExpectancy;
	}
	public void setRemainingLifeExpectancy(Double remainingLifeExpectancy) {
		this.totalLifeExpectancy = remainingLifeExpectancy;
	}
	@Override
	public String toString() {
		return "LifeExpectancy [sex=" + sex + ", country=" + country + ", remainingLifeExpectancy="
				+ totalLifeExpectancy + "]";
	}
}
