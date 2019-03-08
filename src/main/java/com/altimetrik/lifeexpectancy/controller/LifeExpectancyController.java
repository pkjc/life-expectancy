package com.altimetrik.lifeexpectancy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
			
			
			
			
	        return LifeExpectancyService.getMinLifeExpectancyForFemalesByDob(dob);
	    }
		
		@GetMapping("/")
	    public String getHelloWorld() {
			return "Hello World";
	    }
}

