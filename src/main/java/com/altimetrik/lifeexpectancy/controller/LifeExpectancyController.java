package com.altimetrik.lifeexpectancy.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public ResponseEntity<?> getLifeExpectancy(@PathVariable String dob) {
		ResponseEntity<LifeExpectancy> minLifeExpectancyResponse = null;

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
		} else {
			logger.info("ERROR: " + dob);
			throw new BadRequestException(
					"Invalid request argument. Please pass the date of birth in 'yyyy-mm-dd' format.");
		}

		return minLifeExpectancyResponse;
	}
}
