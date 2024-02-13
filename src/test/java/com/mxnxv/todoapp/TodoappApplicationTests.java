package com.mxnxv.todoapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@SpringBootTest
class TodoappApplicationTests {

	@Autowired
	private static WebDriver chrome;

	@AfterAll
	public static void tearDown()
	{
		chrome.quit();
	}

	@Test
	void contextLoads() {
		chrome.get("http://localhost:8091");
	}

}
