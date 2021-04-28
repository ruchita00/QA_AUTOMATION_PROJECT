package Reusable_Functions;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Generic_function {
	public static WebDriver driver;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFCell cell,f;
	public static XSSFRow row;
	public static String CellData,path;
	static File file = new File("configuration/config.properties");
	static Properties prop = new Properties();
	public static int iter; 
	public static int col;

	/* Browser launching using driver that is specified in the config.properties file , navigating to Landing Welcome Page and returning driver object*/
	public static void Browser_Launch() throws IOException {
//		FileInputStream fileInput;
//		fileInput = new FileInputStream(file);
//		prop.load(fileInput);
//			System.setProperty("webdriver.chrome.driver",getDriverPath());
//			driver =new ChromeDriver();
//			
//			driver.navigate().to(getURL());	
//			driver.manage().window().maximize();
		
		FileInputStream fileInput;
		fileInput = new FileInputStream(file);
		prop.load(fileInput);
		System.setProperty("webdriver.chrome.driver",getDriverPath());
		Map<String, Object> prefs = new HashMap<String, Object>();
		//Pass the argument 1 to allow and 2 to block
		prefs.put("profile.default_content_setting_values.notifications", 1);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.navigate().to(getURL());
		driver.manage().window().maximize();

		
		
	}
	
	
	
	/*To get test data iteration value from config.properties file*/
	public static int Dataiter() {            
		iter=Integer.parseInt(prop.getProperty("Data_iteration"));
		return iter;		
	}	

	/* Reading chrome driver path from config.properties file */
	public static String getDriverPath() {
		String driverpath= prop.getProperty("Driverpath");
		if(driverpath!=null) return driverpath ;
		else throw new RuntimeException ("Driverpath is not specified in the Config.properties");
	}

	/* Reading URL from config.properties file */
	public static String getURL() {
		String URL= prop.getProperty("URL");
		if(URL!=null) return URL ;
		else throw new RuntimeException ("URL is not specified in the Config.properties");
	}

	/* Reading Excel file path  from config.properties   */
	public static String getFilepath() {
		String filepath= prop.getProperty("Filepath");
		if(filepath!=null) return filepath ;
		else throw new RuntimeException ("Filepath is not specified in the Config.properties");
	}

	/*To get directory path of screenshots folder*/
	
	public static String getDir() {
		String dirpath= prop.getProperty("Dirpath");
		if(dirpath!=null) return dirpath ;
		else throw new RuntimeException ("user Dir is not specified in the Config.properties");
	}
	
		/*  Taking Screenshot of failed test cases  */
	public static  void takeScreenShot(String fileName) throws IOException {
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(getDir()+fileName+".png"));
	}

	/* Click operation for a particular fieldname that is passing to this function through finding locator value of fieldname using OR_reader function*/
	public static void click(String fieldname) throws IOException {
		driver.findElement(By.xpath(OR_reader("Object_Locator", fieldname))).click();
	}

	/* Refresh function to refresh the current URL opened in browser */
	public static void browser_refresh() {
		driver.navigate().refresh();
	}
	
	/* To wait the browser till the time passed to this function */
	public static void browser_wait(int time) {
		driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
	}
	
	
	/* To find object locator value of a particular fieldname passing to this function by loading Excel workbook*/
	public static  String OR_reader(String sheetname,String Fieldname) throws IOException  {
		File src=new File(getFilepath());
		FileInputStream finput;
		finput = new FileInputStream(src);
		workbook = new XSSFWorkbook(finput);
		sheet = workbook.getSheet(sheetname);
		int rowCount = sheet.getPhysicalNumberOfRows();
		row = sheet.getRow(0);
		for(int i=1;i<rowCount;i++) {
			cell = sheet.getRow(i).getCell(0);
			CellData = cell.getStringCellValue();
			if(CellData.equals(Fieldname))
			{
				f= sheet.getRow(i).getCell(2);
				path = f.getStringCellValue();
				break;
			}
			else
			{
				continue;
			}
		}
		return path;
	}

	/* To read test data value of a particular fieldname passing to this function using findRow function to get row number from excel sheet  */
	public static String td_reader(String fieldname) {
		sheet = workbook.getSheetAt(0);
		col= Dataiter();
		String td_value=sheet.getRow(findRow(fieldname)).getCell(col).getStringCellValue();
		return td_value;
	}
    
	
	/* To read test data value of a particular fieldname using index  where its values are seperated with a comma within cell in excel sheet  */
	public static String td_reader(String fieldname,int index){
		sheet = workbook.getSheetAt(0);
		col= Dataiter();
		String td_value = sheet.getRow(findRow(fieldname)).getCell(col).getStringCellValue();
		String[] str = td_value.split(",");
		//System.out.println(Data_iteration);
		return str[index];
	}

	/* To get row number of a particular fieldname passing to this function from excel sheet  */
	public static int findRow(String fieldname) {
		sheet = workbook.getSheetAt(0);
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getRichStringCellValue().getString().trim().equals(fieldname)) {
					return row.getRowNum();  
				}
			}
		}       
		return 0;
	}
	public static void browser_close() {
		driver.close();
	}
	
}	
