package com.altimetrik.lifeexpectancy.payload;

public class LifeExpectancyResponse {
	
	Double lifeExpectancyAsOfInputDate;
	Double lifeExpectancyAsOfTodayInTheSameCountry;
	
	public LifeExpectancyResponse() {
		
	}
	public LifeExpectancyResponse(Double lifeExpectancyAsOfInputDate, Double lifeExpectancyAsOfTodayInTheSameCountry) {
		super();
		this.lifeExpectancyAsOfInputDate = lifeExpectancyAsOfInputDate;
		this.lifeExpectancyAsOfTodayInTheSameCountry = lifeExpectancyAsOfTodayInTheSameCountry;
	}
	public Double getLifeExpectancyAsOfInputDate() {
		return lifeExpectancyAsOfInputDate;
	}
	public void setLifeExpectancyAsOfInputDate(Double lifeExpectancyAsOfInputDate) {
		this.lifeExpectancyAsOfInputDate = lifeExpectancyAsOfInputDate;
	}
	public Double getLifeExpectancyAsOfTodayInTheSameCountry() {
		return lifeExpectancyAsOfTodayInTheSameCountry;
	}
	public void setLifeExpectancyAsOfTodayInTheSameCountry(Double lifeExpectancyAsOfTodayInTheSameCountry) {
		this.lifeExpectancyAsOfTodayInTheSameCountry = lifeExpectancyAsOfTodayInTheSameCountry;
	}
}
