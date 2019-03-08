package com.altimetrik.lifeexpectancy.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.lifeexpectancy.exception.BadRequestException;
import com.altimetrik.lifeexpectancy.model.LifeExpectancy;
import com.altimetrik.lifeexpectancy.payload.LifeExpectancyResponse;
import com.altimetrik.lifeexpectancy.service.LifeExpectancyService;

@RestController
public class LifeExpectancyController {

	private static final Logger logger = LoggerFactory.getLogger(LifeExpectancyController.class);

	@Autowired
	private LifeExpectancyService LifeExpectancyService;

	// check if input is null or empty string
	// check if given date is in correct format
	// pass on to service
	// check service response is not null or empty
	// check if service throws an exception
	// in case of errors or exception return respective message to user
	// else return the results
	@GetMapping("/api/{dob}")
	public LifeExpectancyResponse getLifeExpectancy(@PathVariable String dob) {
		LifeExpectancyResponse lifeExpectancyResponse = null;
		ResponseEntity<LifeExpectancy> minLifeExpectancyResponse = null;
		ResponseEntity<LifeExpectancy> minLifeExpectancyResponseForToday = null;

		if (dob != null && !dob.isEmpty()) {
			Date dobDate = null;
			try {
				dobDate = new SimpleDateFormat("yyyy-mm-dd").parse(dob);
				logger.info(dob + "\t" + dobDate);
			} catch (ParseException e) {
				logger.info("ERROR: " + dob + "\t" + dobDate);
				throw new BadRequestException(
						"Invalid request argument. Please pass the date of birth in 'yyyy-mm-dd' format.");
			}
			
			minLifeExpectancyResponse = LifeExpectancyService.getMinLifeExpectancyForFemalesByDob(dob);
			minLifeExpectancyResponseForToday = getLifeExpForToday(minLifeExpectancyResponse);

			lifeExpectancyResponse = new LifeExpectancyResponse(
					minLifeExpectancyResponse.getBody().getRemainingLifeExpectancy(),
					minLifeExpectancyResponseForToday.getBody().getRemainingLifeExpectancy());
		} else {
			logger.info("ERROR: " + dob);
			throw new BadRequestException(
					"Invalid request argument. Please pass the date of birth in 'yyyy-mm-dd' format.");
		}
		logger.info("Response: " + minLifeExpectancyResponse.toString());
		return lifeExpectancyResponse;
	}

	private ResponseEntity<LifeExpectancy> getLifeExpForToday(ResponseEntity<LifeExpectancy> minLifeExpectancyResponse) {
		ResponseEntity<LifeExpectancy> minLifeExpectancyResponseForToday;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate todaysDate = LocalDate.now();
		logger.info(dtf.format(todaysDate)); // 2016/11/16

		minLifeExpectancyResponseForToday = LifeExpectancyService.getMinLifeExpectancyForFemalesByToday(
				minLifeExpectancyResponse.getBody().getCountry(), dtf.format(todaysDate));
		return minLifeExpectancyResponseForToday;
	}
}
