package assignment.week5day2;

import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProjectSpecificMethods {
	public RemoteWebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
	
	@DataProvider(name="CreateLeadData")
	public String[][] createLeadData() throws IOException{
		String[][] data = ReadExcel.getData(filePath);
		return data;
	}
	
	@DataProvider(name="DeleteLeadData")
	public String[][] deleteLeadData() throws IOException{
		String[][] data = ReadExcel.getData(filePath);
		return data;
	}
}
