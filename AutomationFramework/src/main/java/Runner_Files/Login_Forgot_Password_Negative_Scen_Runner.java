package Runner_Files;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		features="src\\main\\java\\forgot_pass_negative_tests\\App_func_forgot_password.feature",
		glue= {"forgot_pass_negative_tests"},
		monochrome=true,publish=true)

public class Login_Forgot_Password_Negative_Scen_Runner extends AbstractTestNGCucumberTests {

}


