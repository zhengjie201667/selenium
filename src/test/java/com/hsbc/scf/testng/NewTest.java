package com.hsbc.scf.testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class NewTest {
	WebDriver driver=null;
  @BeforeClass
  public void beforeClass() {
	    System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
  }
  
  @Test
  public void f() throws Exception {
	    driver.get("https://eteams.cn/");
		
		driver.findElement(By.xpath("//*[@id=\"top-btn-login\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("zhengjie6782895@163.com");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("zh6782895");
		driver.findElement(By.xpath("/html/body/section/div/div/form/div[5]/button")).click();
		
		driver.findElement(By.xpath("//*[@id=\"invite-modal\"]/div/div/div[1]/div[2]/ul/li[3]/a")).click();
		Thread.sleep(3000);
		
		String winHandleMain = driver.getWindowHandle();
		System.out.println(winHandleMain);
		System.out.println(driver.getTitle());
		
		WebElement helpElemment = driver.findElement(By.xpath("//*[@id=\"eteamsHelpCenter\"]"));
		WebElement helpCenterElement = driver.findElement(By.xpath("//*[@id=\"eteamsHelpCenter\"]/div/div/div[3]/a"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(helpElemment).click().perform();
		Thread.sleep(3000);
		actions.moveToElement(helpCenterElement).click().perform();
		Thread.sleep(3000);
		
		Set<String> windowSet = driver.getWindowHandles();
		
		for (String winHandle : windowSet) {
			if (winHandle.equals(winHandleMain)) {
				driver.switchTo().window(winHandle);
			}
		}
		
//		driver.findElement(By.xpath("//*[@id=\"search\"]")).sendKeys("工作日报");
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//*[@id=\"search-btn\"]")).click();
//		Thread.sleep(3000);
		driver.close();
//		
//		driver.switchTo().window(winHandleMain);
//		System.out.println();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
