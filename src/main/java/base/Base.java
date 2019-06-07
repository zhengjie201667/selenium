package base;

import static org.hamcrest.CoreMatchers.startsWith;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;





public class Base {
	 
	 public  WebDriver driver;
	 
     public void startBrowser() throws Exception{
    	 //读取properties配置文件的内容
    	 FileInputStream file=new FileInputStream("C:\\Eclipse_workspace\\selenium\\Configs\\GlobalData.properties");
    	 Properties prop = new Properties();
    	 prop.load(file);
    	 String browser = prop.getProperty("browser");
    	 String url=prop.getProperty("url");
    	 if(browser.equals("firefox")){
    		 System.setProperty("webdriver.gecko.driver","D:\\BrowserDriver\\geckodriver.exe");
    		 driver = new FirefoxDriver();		 
    	 } else if(browser.equals("chrome")){
    		 System.setProperty("webdriver.chrome.driver","D:\\BrowserDriver\\chromedriver.exe");
    		 driver = new ChromeDriver();	
    	 }
    	 
    	 driver.get(url);
    	 driver.manage().window().maximize();
    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     }
     
     public static void main(String[] args) throws Exception {
    	 Base base=new Base();
    	 base.startBrowser();
	}
}
