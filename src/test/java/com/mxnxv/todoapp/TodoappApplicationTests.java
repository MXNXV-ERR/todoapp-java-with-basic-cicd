package com.mxnxv.todoapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Configuration
@SpringJUnitConfig
@SpringBootTest
public class TodoappApplicationTests {

	TodoappApplicationTests(){SpringApplication.run(TodoappApplication.class);}

	@Autowired
	private static WebDriver chrome=new ChromeDriver();

	@Bean
	public WebDriver chrome()
	{
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver_latest.exe");
		return new ChromeDriver();
	}
	// @BeforeAll
	// public static void setUp()
	// {
	// 	chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	// }

	@AfterAll
	public static void tearDown(){chrome.quit();}
	
	@Test
	void contextLoads() {
		// System.setProperty("webdriver.chrome.verboseLogging", "true");
		chrome.manage().window().maximize();
		chrome.get("http://127.0.0.1:8091/");
		WebElement createNew = chrome.findElement(By.id("Add-TODO-Item-Buttom"));
		createNew.click();
		WebElement title = chrome.findElement(By.id("title"));
		title.sendKeys("Test Todo");
		WebElement date = chrome.findElement(By.id("date"));
		date.sendKeys("01-01-2014");
		WebElement status = chrome.findElement(By.id("status"));
		status.sendKeys("Not Completed");
		WebElement saveButton = chrome.findElement(By.id("SaveButton"));
		saveButton.click();

		
		


	}
}
