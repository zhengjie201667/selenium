package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePagePO {
    
	public WebDriver driver;
	
	
     public HomePagePO(WebDriver driver) {
		super();
		this.driver = driver;
	}

	//首页登陆按钮
	By loginBtn=By.xpath("//*[@id='top-btn-login']");
	
	public WebElement loginBtn(){
		
		return driver.findElement(loginBtn);
	}
}
