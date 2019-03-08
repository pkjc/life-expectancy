package com.altimetrik.lifeexpectancy;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.altimetrik.lifeexpectancy.controller.LifeExpectancyController;
import com.altimetrik.lifeexpectancy.service.LifeExpectancyService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesTest {

	private static final Logger logger = LoggerFactory.getLogger(ServicesTest.class);
	
	@Autowired
    private LifeExpectancyService service;
	
	@Test
	public void testGetLifeExpectancy() {
		service.getMinLifeExpectancyForFemalesByDob("1952-03-11");
	}

}
