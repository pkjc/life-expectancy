package com.altimetrik.lifeexpectancy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.altimetrik.lifeexpectancy.payload.LifeExpectancyResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpRequestTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void lifeExpShouldReturnMinLifeExpectancyCountry() throws Exception {
    	LifeExpectancyResponse leResponse = this.restTemplate.getForObject("http://localhost:" + port + "/api/1952-03-11/", LifeExpectancyResponse.class);
        assertEquals("65.69071394573783", leResponse.getLifeExpectancyAsOfInputDate());    	
        assertEquals("80.86117678473765", leResponse.getLifeExpectancyAsOfTodayInTheSameCountry());    	
    }
    
    @Ignore
    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Hello World");
    }
    
    @Ignore
    @Test
    public void lifeExpShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/dob",
                String.class)).contains("ok");
    }
}