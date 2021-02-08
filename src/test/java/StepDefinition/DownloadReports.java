package StepDefinition;

import java.io.File;
import java.util.HashMap;

import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import Util.Testutils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class DownloadReports {
	
	WebDriver driver;
	 
	 
	 @Given("^open the application$")
	 
	 public void openApplication()
	 {
		 System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		 
		 Map<String, Object> prefs = new HashMap<String, Object>();
		 
		 
        
	        // Use File.separator as it will work on any OS
	        prefs.put("download.default_directory",
	               System.getProperty("user.dir") + File.separator + "DownloadedReports");
	        
	        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
	         
	        // Adding cpabilities to ChromeOptions
	        ChromeOptions options = new ChromeOptions();
	        options.setExperimentalOption("prefs", prefs);
		    driver = new ChromeDriver(options);
		    
		    driver.manage().window().maximize();
			
			driver.get("http://zotuuxt.cyberprism.ie/auth/login");
			
			driver.findElement(By.id("email")).sendKeys("cyberprism@cri.ie");
			driver.findElement(By.id("password")).sendKeys("t1bg*UgJ^~#p");
			driver.findElement(By.xpath("//button[text()='Login']")).click();
			
			Testutils.expilicitWaittoVisible(driver,"//*[@id='bigTable']/tbody/tr[1]/td[9]/a[1]",50);
			Testutils.scrollDown("window.scrollBy(0,1000)",driver);
			
			driver.findElement(By.xpath("//*[@id='bigTable']/tbody/tr[1]/td[9]/a[1]")).click();
	 }
	 
	 @Then("^download the files in the desired location$")
	 
	 public void downloadFiles() throws InterruptedException
	 {
		 
		WebElement ele = driver.findElement(By.xpath("//*[@id='crumbs']/ul/li[5]/a"));
		 WebElement ele1 = driver.findElement(By.xpath("//div[@id='crumbsadasds']/ul//ul/li/a[@id='docxgeneration']"));
		WebElement ele2 = driver.findElement(By.xpath("//div[@id='crumbsadasds']/ul//ul/li/a[@title='FINSEC Report']")); 
		
		 
		 Actions act = new Actions(driver);
		 
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		
		act.moveToElement(ele).perform(); 
		 
	   js.executeScript("arguments[0].click();", ele1);
			
	   Testutils.windowSwitch(driver); 
		
	  
	     act.moveToElement(ele).perform();	
		js.executeScript("arguments[0].click();", ele2);
	    Testutils.windowSwitch(driver); 
			
			 
			
			 
			 
			 
		}
		 

	 
	 
	 

}
