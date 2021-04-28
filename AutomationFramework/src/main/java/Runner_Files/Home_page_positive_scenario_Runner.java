package Runner_Files;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

	@CucumberOptions(
			plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
			features="src\\main\\java\\home_page_positive_scenario\\Home_page_positive_scenario.feature",
			glue= {"home_page_positive_scenario"},
			monochrome=true,publish=true)

	public class Home_page_positive_scenario_Runner extends AbstractTestNGCucumberTests {


}
