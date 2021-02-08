package Util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;

public class Testutils {
	
	public static long PAGE_LOAD_TIMEOUT =60;
	public static long IMPLICIT_WAIT =50;
	
	
	
	
	
	public static void expilicitWaittoClick(WebDriver driver,String element, int timeout  )
	{
		WebElement	ele=new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		
	
			
		ele.click();
	}
	
	
	public static void expilicitWaittoVisible(WebDriver driver,String element,int timeout )
	{
		 new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOfElementLocated((By.xpath(element))));
			
		
	}

	public static void scrollDown(String down,WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript(down);
	}
	
	public static void scrollUp(String up,WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript(up);
	}
	
	public static void selectDropdown(String webelement ,WebDriver driver, String value)
	{
		driver.findElement(By.xpath(webelement)).click();
		
		Select businsessFactor = new Select(driver.findElement(By.xpath(webelement)));
		
		businsessFactor.selectByVisibleText(value);
	}
	
	public static void windowSwitch(WebDriver driver)
	{
		String mainwindow = driver.getWindowHandle();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        
        while (i1.hasNext()) {
            String ChildWindow = i1.next();
                if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                driver.close();
                
            }
        }    
                driver.switchTo().window(mainwindow);
	}
	
	
	
	
	
	public static void failedScreenshot(WebDriver driver, Scenario scenario) throws IOException
	{
		if (scenario.isFailed()) {
			   try {
			    byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			    File screenshot_with_scenario_name = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			    FileUtils.copyFile(screenshot_with_scenario_name,
			      new File("./target/test-report/" + scenario.getName() + ".png"));
			    System.out.println(scenario.getName());
			    scenario.embed(screenshot, "image/png");
			   } catch (WebDriverException somePlatformsDontSupportScreenshots) {
			    System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			   }
	}
	}

}
