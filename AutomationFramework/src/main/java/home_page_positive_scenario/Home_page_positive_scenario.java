package home_page_positive_scenario;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import Reusable_Functions.Generic_function;
import io.cucumber.java.en.*;

public class Home_page_positive_scenario extends Generic_function {	
	public static boolean value;

	/*Browser is launching*/
	@Given("Browser is open")
	public static void browser_is_open() {
		try {
			Browser_Launch();
			click("welcome_login");	
			driver.findElement(By.xpath(OR_reader("Object_Locator", "login_phone_number"))).sendKeys(td_reader("login_phone_number",0));
			browser_wait(4);		
			driver.findElement(By.xpath(OR_reader("Object_Locator", "login_password"))).sendKeys(td_reader("login_password",0));
			value = driver.findElement(By.xpath(OR_reader("Object_Locator", "login"))).isDisplayed();
			Assert.assertEquals(true,value);
			click("login");
		} catch (IOException e) {
			e.printStackTrace();
		}	 
	}	
/*TC_001 user is navigated to the tabs on clicking any tab*/	
	@Then("user is navigated to the tabs on clicking any tab")
	public static void home_page_positive_scenario_tc_001() throws IOException  {

	//String str= OR_reader("Object_Locator", "common_one");
	boolean walletflag=false;
	boolean billsflag=false;
	boolean awardPointsflag=false;
	int round =0;
	int whilecounter = 0;

	List<WebElement> elements=driver.findElements(By.xpath("//span[@class='MuiBadge-root']//p"));
	int eleSize = elements.size();
	System.out.println("Element size is"+eleSize );

	System.out.println("print1");
	 
	do
	{
		driver.navigate().refresh();
	
		browser_wait(10);
		round++;
		System.out.println("Round "+round);
		elements=driver.findElements(By.xpath("//span[@class='MuiBadge-root']//p"));
	
		for (WebElement element : elements)
		{

			String currentText = element.getText();
			System.out.println("Inside for each current text is "+currentText);
			if(currentText.equalsIgnoreCase("wallet"))
			{
				if( walletflag==false)
				{
				click("wallet");
				browser_wait(5);
				value = driver.findElement(By.xpath(OR_reader("Object_Locator", "wallet_page"))).isDisplayed();
				Assert.assertEquals(true,value,"wallet test case passed");
				System.out.println("wallet test case passed");
				walletflag=true;
				whilecounter++;
				System.out.println("While couner is "+whilecounter);
				click("home");
				break;
			}
	}
				else if(currentText.equalsIgnoreCase("bills"))
			{
			if(billsflag==false)
			{
			click("bills");
			browser_wait(5);
			value = driver.findElement(By.xpath(OR_reader("Object_Locator", "bills_page"))).isDisplayed();
			Assert.assertEquals(true,value,"bills test case passed");
			System.out.println("bill test case passed");
			billsflag=true;
			whilecounter++;
			System.out.println("While couner is "+whilecounter);
			click("home");  
		
			break;
			}

	}
				else if(currentText.equalsIgnoreCase("award points"))
			{
			if(awardPointsflag==false)
			{
			click("award_points");
			browser_wait(5);

			value = driver.findElement(By.xpath(OR_reader("Object_Locator", "award_points_page"))).isDisplayed();
			Assert.assertEquals(true,value,"award points test case passed");
			System.out.println("award points test case passed");
			awardPointsflag=true;
			whilecounter++;
			System.out.println("While couner is "+whilecounter);
			click("home");  
		
			break;
			}
		
			}
		else
				{
				System.out.println("Inside else.....");
				break;
				}
				}
			if(whilecounter == 3)
			{
			System.out.println("Inside terminator.....");
			break;
			}
			} while(whilecounter <= eleSize);
		
			}

	
/*TC_002 User is navigated to the second opinion page on clicking request for second opinion button*/
@Then("User is navigated to the second opinion page on clicking request for second opinion button")
public static void home_page_positive_scenario_tc_002(){
	try {	
		click("second_opinions_button");
		value = driver.findElement(By.xpath(OR_reader("Object_Locator", "second_opinion_button_page"))).isDisplayed();
		Assert.assertEquals(true,value);
		click("home");  
					
	} catch (IOException e) {
		e.printStackTrace();
	}	
}

/*TC_003 User is navigated to the refer a friend page on clicking refer a friend button*/
@Then("User is navigated to the refer a friend page on clicking refer a friend button")
public static void home_page_positive_scenario_tc_003(){
	try {	
		click("refer_a_friend_button");
		value = driver.findElement(By.xpath(OR_reader("Object_Locator", "refer_a_friend_page"))).isDisplayed();
		Assert.assertEquals(true,value);
		click("home");  				
	} catch (IOException e) {
		e.printStackTrace();
	}	
}

/*TC_004 User is navigated to the help page on clicking help tab*/
@Then("User is navigated to the help page on clicking help tab")
public static void home_page_positive_scenario_tc_004(){
	try {
		click("help");
		value = driver.findElement(By.xpath(OR_reader("Object_Locator", "help_page"))).isDisplayed();
		Assert.assertEquals(true,value);
		click("home");				
	} catch (IOException e) {
		e.printStackTrace();
	}	
}

/*TC_005 User is navigated to the  your alerts page on clicking your alerts tab*/
@Then("User is navigated to the  your alerts page on clicking your alerts tab")
public static void home_page_positive_scenario_tc_005() {
	try {
		click("your_alerts");
		value = driver.findElement(By.xpath(OR_reader("Object_Locator", "your_alerts_page"))).isDisplayed();
		Assert.assertEquals(true,value);
		click("home");  

	} catch (IOException e) {
		e.printStackTrace();
	}
}

/*TC_006 User is able to click on the drop down tab*/
@Given("User is able to click on the drop down tab")
public static void home_page_positive_scenario_tc_006(){
	try {
		click("drop_down");
		browser_wait(5);
		click("your_profile_tap");
		value = driver.findElement(By.xpath(OR_reader("Object_Locator", "your_profile_page"))).isDisplayed();
		Assert.assertEquals(true,value);
		click("home");  
	} catch (IOException e) {
		e.printStackTrace();	
	}
}

/*TC_007 User is able to logout on clicking on the logout button*/
@Then("User is able to logout on clicking on the logout button")
public static void home_page_positive_scenario_tc_007(){
	driver.navigate().back();
	try {
		click("logout_home");
		browser_wait(5);
		value = driver.findElement(By.xpath(OR_reader("Object_Locator","logout_page"))).isDisplayed();
		Assert.assertEquals(true,value);
		browser_close();
	} catch (IOException e) {
		e.printStackTrace();

	}
}

}