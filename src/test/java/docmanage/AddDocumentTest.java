package docmanage;

import org.testng.annotations.Test;

import base.Base;
import pagelibraries.LoginPageLib;
import pageobjects.HomePagePO;
import pageobjects.PortalPagePO;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class AddDocumentTest extends Base{
 
  @BeforeClass
  public void beforeClass() throws Exception {
	  startBrowser();
	  	
  }

  @Test
  public void addDocument() throws Exception {
	  //点击首页登陆按钮
//	  HomePagePO homePagePO = new HomePagePO(driver);
//	  homePagePO.loginBtn().click();
//	  LoginPageLib loginPageLib=new LoginPageLib(driver);
//	  loginPageLib.login();
//	  
//	  PortalPagePO portalPagePO = new PortalPagePO(driver);
//	  portalPagePO.invitePeopleClose();
//	  
//	  Actions action = new Actions(driver);
//	  action.moveToElement(portalPagePO.moreApplication()).perform();
//	  
//	  action.moveToElement(portalPagePO.KnowledgeDoc()).perform();
  }
  
  @AfterClass
  public void afterClass() {
  }

}
