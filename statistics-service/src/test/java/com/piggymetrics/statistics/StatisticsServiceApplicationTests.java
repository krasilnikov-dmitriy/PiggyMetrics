package com.piggymetrics.statistics;

import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(classes = StatisticsApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
@WebAppConfiguration
public class StatisticsServiceApplicationTests extends AbstractTestNGSpringContextTests {

	@Test
	public void contextLoads() {
	}

}
