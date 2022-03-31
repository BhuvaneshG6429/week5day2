package assignment.week5day2;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowProjectSpecificMethods {
	public RemoteWebDriver driver;
	public WebDriverWait wait;
	public String filePath;
	
	@Parameters({"URL","BROWSER_NAME"})
	@BeforeMethod
	public void beforeMethod(String url, String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		//open url
		driver.get(url);
		//maximize window
		driver.manage().window().maximize();
		wait= new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
	
	@DataProvider(name="CreateNewIncident")
	public String[][] createNewIncidentData() throws IOException{
		String[][] data = ReadExcel.getData(filePath);
		return data;
	}
	
	@DataProvider(name="UpdateIncident")
	public String[][] updateIncidentData() throws IOException{
		String[][] data = ReadExcel.getData(filePath);
		return data;
	}
	
	@DataProvider(name="AssignIncident")
	public String[][] assignIncidentData() throws IOException{
		String[][] data = ReadExcel.getData(filePath);
		return data;
	}
	
	@DataProvider(name="DeleteIncident")
	public String[][] deleteIncidentData() throws IOException{
		String[][] data = ReadExcel.getData(filePath);
		return data;
	}
}
