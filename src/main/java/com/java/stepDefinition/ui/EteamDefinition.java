package com.java.stepDefinition.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	@And("^I close the pop up page$")
	public void closePopPage() {
		driver.findElement(By.xpath("//*[@class=\"invite-list\"]/li[3]")).click();
	}
	
	@And("^hover above the more button$")
	public void hoverMoreBtn() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"more-menu\"]"))).perform();
		
	}
	
	@And("^click the knowledge btn$")
	public void clickKnowledgeBtn() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='知识文档']")));
		driver.findElement(By.xpath("//*[@title='知识文档']")).click();
	}
	
	
	public static void main(String[] args) throws IOException {
		Properties properties = new Properties();
		File file = new File("config.property");
		InputStream inputStreamStream = new FileInputStream(file);
		properties.load(inputStreamStream);
		System.out.println(properties.getProperty("password"));
		
	}
}
