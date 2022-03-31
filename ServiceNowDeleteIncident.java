package assignment.week5day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ServiceNowDeleteIncident extends ServiceNowProjectSpecificMethods{
	
	@BeforeTest
	public void setData(){
	filePath = "./testData/DeleteIncidentServiceNow.xlsx";
	
	}
	
	@Test(dataProvider = "DeleteIncident")
	public void deleteIncident(String username, String password, String filter, String incident) throws IOException, InterruptedException {
//		Step2: Enter username (Check for frame before entering the username)
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		driver.findElement(By.id("user_name")).sendKeys(username);
//		Step3: Enter password
		driver.findElement(By.id("user_password")).sendKeys(password);
//		Step4: Click Login
		driver.findElement(By.xpath("//button[@id='sysverb_login']")).click();
		driver.switchTo().defaultContent();
//		Step5: Search “incident “ Filter Navigator
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filter")));
		WebElement filterNavSearch = driver.findElement(By.id("filter"));
		filterNavSearch.sendKeys(filter,Keys.ENTER);
		
//		Step6: Click “All”
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='filter']//following::span[text()='"+filter+"'])[1]")));
		driver.findElement(By.xpath("(//span[text()='"+filter+"']//following::div[text()='All'])[1]")).click();
		
		//search existing incident number
		driver.switchTo().frame("gsft_main");
		WebElement selectListBox = driver.findElement(By.xpath("(//span[@id='incident_hide_search']//select)[1]"));
		Select select = new Select(selectListBox);
		select.selectByVisibleText("Number");
		
		WebElement searchInc = driver.findElement(By.xpath("(//span[@id='incident_hide_search']//input)[1]"));
		searchInc.sendKeys(incident,Keys.ENTER);

		WebElement incLink = driver.findElement(By.xpath("(//table[@id='incident_table']//a[@class='linked formlink'])[1]"));
		String incidentResultNumber = incLink.getText();
		incLink.click();
		//wait until page loads
		WebElement deleteButton = driver.findElement(By.id("sysverb_delete"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sysverb_delete")));
		
		//screenshot before updating the assignment group
		File screenshot1 = driver.getScreenshotAs(OutputType.FILE);
		File imageBefore = new File("./images/serviceNow/"+incidentResultNumber+"_beforeDeleteIncident"+".jpg");
		FileUtils.copyFile(screenshot1, imageBefore);
		
		//delete the incident
		deleteButton.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='delete_confirm_form']")));
		driver.findElement(By.xpath("//div[@id='delete_confirm_form']//button[text()='Delete']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@id='incident_hide_search']//select)[1]")));
		WebElement selectListBox1 = driver.findElement(By.xpath("(//span[@id='incident_hide_search']//select)[1]"));
		Select select1 = new Select(selectListBox1);
		select1.selectByVisibleText("Number");
		
		WebElement searchInc1 = driver.findElement(By.xpath("(//span[@id='incident_hide_search']//input)[1]"));
		searchInc1.sendKeys(incident,Keys.ENTER);
		
		WebElement recordsDisplay = driver.findElement(By.xpath("//td[contains(text(),'No records to display')]"));
		if(recordsDisplay.isDisplayed()) {
			System.out.println("Incident has been deleted");
		}
		
		//screenshot after the updating the assignment group
		File screenshot2 = driver.getScreenshotAs(OutputType.FILE);
		File imageAfter = new File("./images/serviceNow/"+incidentResultNumber+"_afterDeleteIncident"+".jpg");
		FileUtils.copyFile(screenshot2, imageAfter);
}
}
