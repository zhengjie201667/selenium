package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PortalPagePO {
	
	public WebDriver driver;
	
	 //定义构造方法接收driver
	public PortalPagePO(WebDriver driver) {
		this.driver = driver;		
	}
	
	By invitePeopleClose = By.xpath("//a[@class='close' and @data-dismiss='modal']");
	By moreApplication = By.linkText("更多应用");
	By knowledgeDoc = By.xpath("//a[@title='知识文档']");
	
	
    //邀请同事窗口关闭按钮
	public WebElement invitePeopleClose() {
		
		return driver.findElement(invitePeopleClose);
	}
	
	//更多应用
	public WebElement moreApplication() {
		
		return driver.findElement(moreApplication);
	}
	
	//知识文档
	public WebElement KnowledgeDoc() {
		
		return driver.findElement(knowledgeDoc);
	}
}
