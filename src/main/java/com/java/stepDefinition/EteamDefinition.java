package com.java.stepDefinition;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Driver;
import java.util.Properties;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class EteamDefinition {
	public static WebDriver driver = null;

	@Given("^open the Eteam homepage$")
	public void openHomePage() {
		System.setProperty("webdriver.chrome.driver","src\\test\\resources\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://eteams.cn");
		driver.manage().window().maximize();
		
	}
	
	@And("^login the page$")
	public void loginPage() {
		Properties properties = new Properties();
		File file = new File("config.property");
		InputStream inputStreamStream;
		try {
			inputStreamStream = new FileInputStream(file);
			properties.load(inputStreamStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id=\"top-btn-login\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(properties.getProperty("usename"));
		driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(properties.getProperty("password"));
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[5]/button")).click();
	}
}
