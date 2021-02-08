package Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserIntiate {
	
WebDriver driver;
	 
	
	public  WebDriver chromeInvoke() 
	{
		
		
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

	       	 driver = new ChromeDriver();
	          
			return driver;
	
	 
	}
	

}
