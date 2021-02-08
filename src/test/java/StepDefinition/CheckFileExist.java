package StepDefinition;

import java.io.File;

import cucumber.api.java.en.Given;

public class CheckFileExist {
	
	@Given("^Check the file is present in the DownloadReport folder$")
	public void checkfileIsPresent()
	{
		String Path="/DownloadedReports";
		
	File dir = new File(System.getProperty("user.dir")+Path);
	  File[] dirContents = dir.listFiles();
	  
	  
	  String[] Filename= {"CyberPrism-FINSEC-Report-Demo-Corporate-HQ-20-05-2020.docx",
			  "CyberPrism-Full-Report-Demo-Corporate-HQ-20-05-2020.docx"};
	  

	  for (int i = 0; i<dirContents.length; i++) {
	      if (dirContents[i].getName().equals(Filename[i])) {
	         
	    	  System.out.println("The file "+dirContents[i].getName()+" is present");
	      }
	      
	      else
	      {
	    	  System.out.println("The file "+dirContents[i].getName()+" is not present");
	      }
	          
	  
	}
		
	}


}
