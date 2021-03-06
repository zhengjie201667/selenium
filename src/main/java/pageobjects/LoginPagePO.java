package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPagePO {
	
      public WebDriver driver;
      
      By username = By.id("username");
      By password = By.id("password");
      By submitBtn = By.xpath("//button[@type='submit']");
	   
      public LoginPagePO(WebDriver driver) {
		super();
		this.driver = driver;
	  }
    //*[@id="invite-modal"]/div/div/div[1]/div[2]/ul/li[3]/a
      public WebElement uerName(){
    	  return driver.findElement(username);
      }
      
      public WebElement password(){
    	  return driver.findElement(password);
      }
      
      public WebElement submitBtn() {
		  return driver.findElement(submitBtn);
	  }
}
