package com.mxnxv.todoapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Configuration
@SpringJUnitConfig
@SpringBootTest
public class TodoappApplicationTests {

	static ChromeDriver chrome ;

	TodoappApplicationTests(){SpringApplication.run(TodoappApplication.class);}

		@BeforeAll
		public static void load(){
				ChromeOptions opts = new ChromeOptions();
				opts.addArguments("--headless");
				opts.addArguments("--no-sandbox");
				opts.addArguments("window-size=1080,1920");
				chrome =  new ChromeDriver(opts);
		}
	
		@AfterAll
		public static void tearDown(){chrome.quit();}
		
		@Test
		void contextLoads() {
			chrome.manage().window().maximize();
			chrome.get("http://127.0.0.1:8091/");
			WebElement createNew = chrome.findElement(By.id("Add-TODO-Item-Buttom"));
			createNew.click();
			WebElement title = chrome.findElement(By.id("title"));
			title.sendKeys("Test Todo");
			WebElement date = chrome.findElement(By.id("date"));
			date.sendKeys("01-01-2014");
			WebElement saveButton = chrome.findElement(By.id("SaveButton"));
			saveButton.click();
	}
}
