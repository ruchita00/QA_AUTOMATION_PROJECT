package forgot_pass_negative_tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;

import Reusable_Functions.Generic_function;
import io.cucumber.java.en.*;

public class App_func_forgot_password extends Generic_function {
	public static String str;
	/*Browser is launching*/
	@Given("Browser is open")
	public static void browser_is_open() {
		try{		

			Browser_Launch();
		}catch (IOException e) {
			System.err.println("Caught IOException: " +  e.getMessage());
		}	 
	}	

	/*TC_001 User get the validation message when Phone number field is blank */
	@Then("User get the validation message when Phone number field is blank")
	public void forgot_password_negative_tc_001() throws InterruptedException  {
		try {
			driver.findElement(By.xpath(OR_reader("Object Locator1","login"))).click();	
			driver.findElement(By.xpath(OR_reader("Object Locator1","forgot_password"))).click();	
			driver.findElement(By.xpath(OR_reader("Object Locator1","phone_number"))).click();
			driver.findElement(By.xpath(OR_reader("Object Locator1","forgot_password_val"))).click();
		    str= driver.findElement(By.xpath(OR_reader("Object Locator1","phone_number_blank"))).getText();
			Assert.assertEquals(str,"Phone number required");
		    driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS);	

			browser_refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*TC_002 User get the validation message on entering less than ten digit phone number*/
	@Given("User get the validation message on entering less than ten digit phone number")
	public void forgot_password_negative_tc_002() {
		try {
			driver.findElement(By.xpath(OR_reader("Object Locator1","phone_number"))).sendKeys(td_reader("phone_number",0));
			driver.findElement(By.xpath(OR_reader("Object Locator1","send_reset_link"))).click();
		    str= driver.findElement(By.xpath(OR_reader("Object Locator1","phone_number_val"))).getText();
			Assert.assertEquals(str,"Phone number should be 10 digit number");
			driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS);	
			browser_refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    /*TC_003 User get the validation message on entering more than ten digit phone number*/
	@Given("User get the validation message on entering more than ten digit phone number")
	public void forgot_password_negative_tc_003() {
		try {
			driver.findElement(By.xpath(OR_reader("Object Locator1", "phone_number"))).sendKeys(td_reader("phone_number",1));
			driver.findElement(By.xpath(OR_reader("Object Locator1","send_reset_link"))).click();
	        str= driver.findElement(By.xpath(OR_reader("Object Locator1","phone_number_val"))).getText();
			Assert.assertEquals(str,"Phone number should be 10 digit number");
				driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS);	
			browser_refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
   /*TC_004 Send Reset Link button is disabled when Phone number field is blank*/
	@Then("Send Reset Link button is disabled when Phone number field is blank")
	public void forgot_password_negative_tc_004() {
		try {
			Assert.assertEquals(driver.findElement(By.xpath(OR_reader("Object Locator1","send_reset_link"))).isEnabled(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
    
	/*TC_005 User should able to get a validation message on entering non registered phone number in the phone number field*/
	@Then("User should able to get a validation message on entering non registered phone number in the phone number field")
	public void forgot_password_negative_tc_005() {
		try {
			driver.findElement(By.xpath(OR_reader("Object Locator1","phone_number"))).sendKeys(td_reader("phone_number",2));
			driver.findElement(By.xpath(OR_reader("Object Locator1","send_reset_link"))).click();
			str= driver.findElement(By.xpath(OR_reader("Object Locator1","phone_number_inv"))).getText();
	        Assert.assertEquals(str,"Phone number doesn't exists");
			browser_close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	}
