package Runner;
import org.junit.runner.RunWith;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/*@RunWith(ExtendedCucumber.class)

@ExtendedCucumberOptions(
		   
		               retryCount=1
		               )*/


@RunWith(Cucumber.class)
	
	@CucumberOptions(
			features = "src/test/resources/Features/CheckFile.feature",
			//the path of the feature files
			glue={"StepDefinition"},//the path of the step definition files
			plugin= {"rerun:target/rerun.txt","json:target/cucumber-reports/cucumber.json"}
			//format= {"pretty","html:test-output"}, //to generate different types of reporting
			//monochrome = true, //display the console output in a proper readable format
			//strict = true, //it will check if any step is not defined in step definition file
			//dryRun = false //to check the mapping is proper between feature file and step def file
			//tags = {"~@SmokeTest" , "~@RegressionTest", "~@End2End"}			
			)




public class TestRunner {

}


