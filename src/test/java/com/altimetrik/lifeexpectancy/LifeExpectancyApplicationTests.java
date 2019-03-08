package com.altimetrik.lifeexpectancy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import com.altimetrik.lifeexpectancy.controller.LifeExpectancyController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LifeExpectancyApplicationTests {

	@Autowired
    private LifeExpectancyController controller;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

}
