package StepDefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Base.BrowserIntiate;
import Util.ExcelUtil;
import Util.Testutils;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Assesment extends BrowserIntiate  {
	
	
    WebDriver driver;
    
    



@Given("^Login to the application$")
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








@Then ("^select the specific assessment to answer the stage1 questions$")
public void select_the_assessment_stage1(DataTable credentials)
{
   
	driver.manage().timeouts().implicitlyWait(Testutils.IMPLICIT_WAIT, TimeUnit.SECONDS);
	
   Testutils.scrollDown("window.scrollBy(0,2000)",driver);
   
	for (Map<String, String> data : credentials.asMaps(String.class, String.class)) {
		
		List<WebElement> rows=driver.findElements(By.xpath("//table[@id='bigTable']/tbody/tr"));
		
		
		
		for(int i=1;i<=rows.size();i++)
		{
			
			String assmtName=driver.findElement(By.xpath("//table[@id='bigTable']/tbody/tr["+i+"]/td[2]")).getText();
			
		
			
			if(assmtName.equals(data.get("Assessment_Name")))
				
			{
				
				driver.findElement(By.xpath("//table[@id='bigTable']/tbody/tr["+i+"]/td[9]/a[1]")).click();
			}
		}
		
		
		
	}
	
	
}

@Then("^Fill the answers to the stage one questions$")
public void fill_the_answers_to_stage1_questions() throws IOException, InterruptedException  {
	
	
	
	
	int rowCount = ExcelUtil.getRowCount("Sheet1","C:\\\\eclipse-workspace\\\\Cyberprism\\\\DataUtil\\\\Exceldata.xlsx");
	
	System.out.println(rowCount);
	
	for (int rowNum=2; rowNum<=rowCount;rowNum++)
	{
		String St1Question = ExcelUtil.getStringCellData("Sheet1", "Question",rowNum);
		String St1Answer = ExcelUtil.getStringCellData("Sheet1", "Answer",rowNum);
		String St1Notes = ExcelUtil.getStringCellData("Sheet1", "Notes",rowNum);
		String St1Attachment = ExcelUtil.getStringCellData("Sheet1", "Attachment",rowNum);;
				
		
		
		
	
	driver.findElement(By.xpath("//input[@id='userBasedSearchStage1']/following::input[@name='fname'][1]")).sendKeys(St1Question);
	
	//driver.findElement(By.xpath("//a[@class='select_question btn btn-success']")).click();
	
	Testutils.expilicitWaittoClick(driver,"//a[@class='select_question btn btn-success']",50);
	 Testutils.scrollDown("window.scrollBy(0,2000)",driver);
	
	
	driver.findElement(By.xpath("//button[@id='dropdownMenu1']")).click();

	Testutils.expilicitWaittoVisible(driver,"//ul[@class='dropdown-menu custom-dropdown']",50);
	
	switch(St1Answer)
	{
	
	case "Least":
		
		driver.findElement(By.xpath("//ul[@class='dropdown-menu custom-dropdown']/li[1]/a/h4")).click();
		
		break;
		
   case "Minimal":
   	
   	driver.findElement(By.xpath("//ul[@class='dropdown-menu custom-dropdown']/li[2]/a/h4")).click();
		
		break;
		
   case "Moderate":
   	
   	driver.findElement(By.xpath("//ul[@class='dropdown-menu custom-dropdown']/li[3]/a/h4")).click();
		
		break;
		
   case "Significant":
   	
   	driver.findElement(By.xpath("//ul[@class='dropdown-menu custom-dropdown']/li[4]/a/h4")).click();
		
		break;
		
   case "Most":
   	
   	driver.findElement(By.xpath("//ul[@class='dropdown-menu custom-dropdown']/li[5]/a/h4")).click();
		
		break;
		
		
	}
	
	
	driver.findElement(By.xpath(("(//textarea[@class='form-control ng-pristine ng-untouched ng-valid ng-not-modified'])[1]"))).sendKeys(St1Notes);
	
	if(StringUtils.isEmpty(St1Attachment))
	{
		Testutils.expilicitWaittoClick(driver,"//button[text()='Save and Continue ']",50);
		//driver.findElement(By.xpath("//button[text()='Save and Continue ']")).click();
	}
	
	else
	{
	
	driver.findElement(By.xpath("(//input[@type='file'])[1]")).sendKeys(System.getProperty("user.dir")+St1Attachment);
	
    Testutils.expilicitWaittoClick(driver,"//button[text()='Save and Continue ']",50);
	}

driver.findElement(By.xpath("//input[@id='userBasedSearchStage1']/following::input[@name='fname'][1]")).clear();
	
	}
	
String leftCount=	driver.findElement(By.xpath("//span[@class='unanswered_count_inherent_risk ng-binding']")).getText();

Assert.assertEquals(leftCount, "0");
	
}






@Then ("^select the specific assessment to answer the stage2 questions$")
public void select_the_assessment_stage2(DataTable credentials)
{
   
	driver.manage().timeouts().implicitlyWait(Testutils.IMPLICIT_WAIT, TimeUnit.SECONDS);
	
   Testutils.scrollDown("window.scrollBy(0,2000)",driver);
   
	for (Map<String, String> data : credentials.asMaps(String.class, String.class)) {
		
		List<WebElement> rows=driver.findElements(By.xpath("//table[@id='bigTable']/tbody/tr"));
		
		
		
		for(int i=1;i<=rows.size();i++)
		{
			
			String assmtName=driver.findElement(By.xpath("//table[@id='bigTable']/tbody/tr["+i+"]/td[2]")).getText();
			
		
			
			if(assmtName.equals(data.get("Assessment_Name")))
				
			{
				
				driver.findElement(By.xpath("//table[@id='bigTable']/tbody/tr["+i+"]/td[9]/a[1]")).click();
			}
		}
		
		
		
	}
	
	
}

@Then("^Fill the answers to the stage two questions$")
public void fill_the_answer_to_stage2_questions() throws IOException, InterruptedException {
	
	
	//Testutils.scrollUp("window.scrollBy(0,-2000)",driver);
	driver.findElement(By.xpath("//a[@class='cybermtcls']")).click();
	
	int rowCount=ExcelUtil.getRowCount("Sheet2","C:\\eclipse-workspace\\Cyberprism\\DataUtil\\Exceldata.xlsx");
	
	for (int rowNum=2; rowNum<=rowCount;rowNum++)
	{
		String St2Question = ExcelUtil.getStringCellData("Sheet2", "Question",rowNum);
		String St2Answer = ExcelUtil.getStringCellData("Sheet2", "Answer",rowNum);
		String St2Notes = ExcelUtil.getStringCellData("Sheet2", "Notes",rowNum);
		String St2Attachment = ExcelUtil.getStringCellData("Sheet2", "Attachment",rowNum);
		
		
		driver.findElement(By.xpath("//input[@id='userBasedSearchStage1']/following::input[@name='fname'][2]")).sendKeys(St2Question);
		
		Testutils.expilicitWaittoClick(driver,"//a[@class='cyber_select_questions select_button btn btn-success cm_bd_select']",50);
		
		
		
		
		Testutils.scrollDown("window.scrollBy(0,2000)",driver);
		
		
		
		driver.findElement(By.xpath("//select[@id='declaration_answer']")).click();
		
		Testutils.expilicitWaittoVisible(driver,"//select[@id='declaration_answer']",50);
		
		Select st2Dropdown = new Select(driver.findElement(By.xpath("//select[@id='declaration_answer']")));
		
		
		
		st2Dropdown.selectByVisibleText(St2Answer);
		
		driver.findElement(By.xpath(("( //form[@id='reload'])[2]/div[2]/div[3]/div/textarea"))).sendKeys(St2Notes);	
		
		if(StringUtils.isEmpty(St2Attachment))
		{
		
			Testutils.expilicitWaittoClick(driver,"//button[@class='cyber_save_continue_button btn btn-success pull-right']",50);
			
			
		}
		
		else
		{
			
		driver.findElement(By.xpath("//*[@id=\"reload\"]/div[2]/div[5]/div/input")).sendKeys(System.getProperty("user.dir")+St2Attachment);
			
		Testutils.expilicitWaittoClick(driver,"//button[@class='cyber_save_continue_button btn btn-success pull-right']",50);
		
		}
		
		driver.findElement(By.xpath("//input[@id='userBasedSearchStage1']/following::input[@name='fname'][2]")).clear();
		
		

}

}	





@After
public void takeScreenshot(Scenario scenario) throws IOException
{
Testutils.failedScreenshot(driver, scenario);
}

	


}
