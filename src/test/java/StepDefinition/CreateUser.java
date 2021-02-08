package StepDefinition;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import Base.BrowserIntiate;
import Util.Testutils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateUser extends BrowserIntiate {
	

	WebDriver driver;
    
	
	@Given("^Then Sign to the application$")
	public void Login_to_the_application(DataTable credentials) throws InterruptedException 
	{
		for (Map<String, String> data : credentials.asMaps(String.class, String.class)) {
		driver= chromeInvoke();
		driver.manage().window().maximize();
		
		driver.get(data.get("Url"));
		driver.findElement(By.id("email")).sendKeys(data.get("Username"));
		driver.findElement(By.id("password")).sendKeys(data.get("Password"));
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		
		
		}
		
		Testutils.expilicitWaittoVisible(driver,"//a[text()='CRI-Admin ']",50);
		
		String adminname=	driver.findElement(By.xpath("//a[text()='CRI-Admin ']")).getText();
		System.out.println(adminname);
		
		
		Assert.assertEquals(adminname, "CRI-ADMIN");
		
	}
	
	@When("^click the user link in the menu$")
	
	public void clickUserLink()
	{
		
		driver.findElement(By.xpath("//a[text()='CRI-Admin ']")).click();
		Testutils.expilicitWaittoVisible(driver,"//a[text()='Users']",50);
		driver.findElement(By.xpath("//a[text()='Users']")).click();
		
		
	}
	
	
	@And("^click the add new user button$")
	
	public void clickAddNewuser()
	{
		driver.findElement(By.xpath("//a[text()='New User']")).click();
	}

	
	@Then("^fill the user form and click submit$")
	
	public void fillUserForm(DataTable userData) 
	{
		for (Map<String, String> data : userData.asMaps(String.class, String.class)) 
		
		{
			
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(data.get("Name"));
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(data.get("Email"));
		
		String [] userType= {"Is Admin","Is Manager","Standard Account"};
		
		
		    if(userType[0].equals(data.get("UserType")))
		    {
			
		    	driver.findElement(By.xpath("//input[@name='admin']")).click();
			
		   }
		
		    else if(userType[1].equals(data.get("UserType")))
		    {
			   
			driver.findElement(By.xpath("//input[@name='manager']")).click();
			
		     }
		
		    else  if(userType[2].equals(data.get("UserType")))
		    {
		    	 
			
			driver.findElement(By.xpath("//input[@name='standard']")).click();
			
		      }
		    
		    String userSecurity[]= {"Force change password","Two Factor Authentication"};
			
			if(userSecurity[0].equals(data.get("UserSecurity")))
		    {
			
		    	driver.findElement(By.xpath("//input[@name='force_change_password']")).click();
			
		   }
			else if(userSecurity[1].equals(data.get("UserSecurity")))
		    {
			
		    	driver.findElement(By.xpath("//input[@name='tfa']")).click();
			
		   }
		}
		
		driver.findElement(By.xpath("//button[text()=' Register ']")).click();
		
		
	}
	
	@Then("^verify the user is created successfully or not$")
	
	public void verifytheUser(DataTable usertable)
	{

		
		SoftAssert softassert = new SoftAssert();
	
for (Map<String, String> data : usertable.asMaps(String.class, String.class)) {
			
			List<WebElement> rows=driver.findElements(By.xpath("//table[@class='table table-striped table-responsive table-hover']/tbody/tr"));
			
			for(int i=1;i<=rows.size();i++)
			{
			String userVerify=	driver.findElement(By.xpath("//table[@class='table table-striped table-responsive table-hover']/tbody/tr["+i+"]/td[1]")).getText();
			
			softassert.assertEquals(userVerify, data.get("Name"));
			}
	}
	
}

}
