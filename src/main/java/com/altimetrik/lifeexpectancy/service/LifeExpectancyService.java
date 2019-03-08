package com.altimetrik.lifeexpectancy.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.altimetrik.lifeexpectancy.model.Country;
import com.altimetrik.lifeexpectancy.model.LifeExpectancy;

@Service
public class LifeExpectancyService {
	
	private static final Logger logger = LoggerFactory.getLogger(LifeExpectancyService.class);
	private static final String BASE_URL = "http://api.population.io:80/1.0";
	private static final String LIFE_EXPECTANCY_ENDPOINT = "/life-expectancy";
	private static final String COUNTRIES_ENDPOINT = "/countries";
	private static final String GENDER = "female";
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public static void main(String[] args) {
		new LifeExpectancyService().getMinLifeExpectancyForFemalesByDob("1952-03-11");
	}
	
	// access population api
	// get list of countries
	// pass each country to life expectancy endpoint 
	// add results to an object
	// loop over results to find the country with the lowest LE
	// return results
	// in case of errors/exception throw appropriate exception
	//http://api.population.io:80/1.0/life-expectancy/total/male/United Kingdom/1952-03-11/;
	//http://api.population.io:80/1.0/countries
	public ResponseEntity<LifeExpectancy> getMinLifeExpectancyForFemalesByDob(String dob) {
		
		ResponseEntity<LifeExpectancy> lifeExpectancyResponse = null;
		ResponseEntity<LifeExpectancy> minLifeExpectancyResponse = null;
		
		Double minLifeExpectancy = Double.MAX_VALUE;
		
		ResponseEntity<Country> countriesResponse = getCountriesFromApi();
		
		logger.info("countriesResponse: " + countriesResponse.getBody().getCountries());
		
		for (String country : countriesResponse.getBody().getCountries()) {
			lifeExpectancyResponse = getLifeExpectancyForCountryAndDob(country, dob);
			if(lifeExpectancyResponse != null) {
				logger.info("lifeExpectancyResponse: " + lifeExpectancyResponse.getBody().toString());
				if(lifeExpectancyResponse.hasBody()) {
					//Double lifeExp = Double.parseDouble(lifeExpectancyResponse.getBody().getRemainingLifeExpectancy());
					if(lifeExpectancyResponse.getBody() !=null && lifeExpectancyResponse.getBody().getRemainingLifeExpectancy() < minLifeExpectancy) {
						minLifeExpectancyResponse = lifeExpectancyResponse;
					}
				}
			}
		}
		if(minLifeExpectancyResponse!= null)
			logger.info("MIN lifeExpectancyResponse: " + minLifeExpectancyResponse.getBody().toString());
		
		return minLifeExpectancyResponse;
	}
	
	private ResponseEntity<Country> getCountriesFromApi(){
		StringBuilder uriForCountries = new StringBuilder();
		uriForCountries.append(BASE_URL).append(COUNTRIES_ENDPOINT);
		logger.info("uriForCountries formed: " + uriForCountries);
		ResponseEntity<Country> response = restTemplate.exchange(uriForCountries.toString(), HttpMethod.GET, null, Country.class);
		return response;
	}

	private ResponseEntity<LifeExpectancy> getLifeExpectancyForCountryAndDob(String country, String dob){
		ResponseEntity<LifeExpectancy> response = null;
		try {
			StringBuilder uriForLifeExpectancy = new StringBuilder();
			country = country.replaceAll("[^A-Za-z0-9 ]", " ").trim().toLowerCase();
			country = WordUtils.capitalize(country);
			
			uriForLifeExpectancy.append(BASE_URL)
			.append(LIFE_EXPECTANCY_ENDPOINT)
			.append("/total/")
			.append(GENDER)
			.append("/")
			.append(country)
			.append("/")
			.append(dob);
			
			response = restTemplate.exchange(uriForLifeExpectancy.toString(), HttpMethod.GET, null, LifeExpectancy.class);
			
			logger.info("uriForLifeExpectancy formed: " + uriForLifeExpectancy);
		} catch (RestClientException e) {
			logger.info("Erro occurred: while fetching life expectancy: " + e.getMessage());
		}
		
		return response;
	}
}


