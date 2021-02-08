package StepDefinition;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.testng.Assert;

import Base.BrowserIntiate;
import Util.Testutils;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateNewAssessment extends BrowserIntiate {
	
	 WebDriver driver;
    
    
		
		
		
		@Given("^Sign to the application$")
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
		
		
		@Given("^click the create new assesment button$")
		
		public void clickCreateNewAssesment()
		{
			 Testutils.scrollDown("window.scrollBy(0,2000)",driver);
			 
			 driver.findElement(By.xpath("//a[@id='createAssessment']")).click();
		}
		
		@When("^the user fill the form details and submit$")
		
		public void fillAssesmentForm(DataTable AssesmentDetails)
		{
			Testutils.scrollDown("window.scrollBy(0,2000)",driver);
			
			for (Map<String, String> data : AssesmentDetails.asMaps(String.class, String.class)) 
			{
				
				driver.findElement(By.xpath("//input[@id='organisation_name']")).sendKeys(data.get("OrganisationName"));
				
				driver.findElement(By.xpath("//input[@id='organisation_logo']")).sendKeys(System.getProperty("user.dir")+data.get("OrganisationLogo"));
				
				Testutils.selectDropdown("//select[@id='business_factor']", driver, data.get("BusinessFactor"));
				
				Testutils.selectDropdown("//select[@id='country_index']", driver, data.get("CountryIndex"));
				
				driver.findElement(By.xpath("//input[@id='address']")).sendKeys(data.get("Address"));
				
				driver.findElement(By.xpath("//button[text()='Find']")).click();
				
				driver.findElement(By.xpath("//textarea[@id='assessor_scope']")).sendKeys(data.get("Scope_of_Audit"));
				
				driver.findElement(By.xpath("//input[@id='category']")).sendKeys(data.get("Category"));
				
				driver.findElement(By.xpath("//input[@id='security_classification']")).sendKeys(data.get("SecurityClassification"));
				
				driver.findElement(By.xpath("//input[@id='assessor_name']")).sendKeys(data.get("AssessorName"));
				
				driver.findElement(By.xpath("//input[@id='assessor_title']")).sendKeys(data.get("AssessorTitle"));
				
				//Testutils.scrollDown("window.scrollBy(0,2000)",driver);
				
				driver.findElement(By.xpath("//input[@id='assessor_email']")).sendKeys(data.get("AssessorEmail"));
				
				Testutils.selectDropdown("//select[@class='user_list form-control']", driver, data.get("AssignUser"));
				
				driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
				
			}
		}
		
		
		@Then ("^verify the new assesment created succesfully$")
		
		public void verifyAssesmentName(DataTable Assesmentverify)
		{
			
			for (Map<String, String> data : Assesmentverify.asMaps(String.class, String.class)) 
			{
		
		
			Testutils.scrollDown("window.scrollBy(0,-2000)",driver);
			
		String assementText=driver.findElement(By.xpath("//span[@class='assement-title']")).getText();
		
		assementText=	assementText.substring(0, assementText.length()-14);
		
		String text1[]=assementText.split(":");
		
		String capturedText=text1[1].trim();
		
         Assert.assertEquals(capturedText, data.get("AssesmentName") );
         
         System.out.println("Assesment name is verified");
			
			}
			
		}
		
		


}
