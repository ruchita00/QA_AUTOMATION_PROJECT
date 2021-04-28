package Runner_Files;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
@CucumberOptions(
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		features = "src\\main\\java\\Feature_Files\\Land_Wel_page.feature",
		glue= {"App_Script_Lan_wel_page"},
		monochrome = true,publish = true)

public class Land_Wel_page_Runner extends AbstractTestNGCucumberTests{

}
