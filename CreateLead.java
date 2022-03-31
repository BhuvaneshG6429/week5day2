package assignment.week5day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLead extends ProjectSpecificMethods{
	
				@BeforeTest
				public void setData(){
				filePath = "./testData/CreateLead.xlsx";
				
				}
			
				@Test(dataProvider = "CreateLeadData")
				public void createLead(String usernameData,String passwordData,String companyNameData,String firstNameData,String lastNameData, String localNameData, String deptData, String descData, String numberData, String mailData, String provinceData) {
						
				//System.out.println(driver.getTitle());
						
				System.out.println(driver.findElement(By.tagName("title")).getText());
				
				
				//enter username
				WebElement username = driver.findElement(By.id("username"));
				username.sendKeys(usernameData);
				//enter password
				WebElement password = driver.findElement(By.id("password"));
				password.sendKeys(passwordData);
				//click Login button
				WebElement login = driver.findElement(By.className("decorativeSubmit"));
				login.click();
				//click CRM/SFA linkText
				WebElement linkTextCRM = driver.findElement(By.linkText("CRM/SFA"));
				linkTextCRM.click();
				
				//click Leads
				WebElement leads = driver.findElement(By.linkText("Leads"));
				leads.click();
				
				//click on Create Lead from Side Menu
				WebElement createLead = driver.findElement(By.linkText("Create Lead"));
				createLead.click();
				
				//Enter mandatory fields to create lead
				WebElement companyName = driver.findElement(By.id("createLeadForm_companyName"));
				companyName.sendKeys(companyNameData);
				
				//Enter first name
				WebElement firstName = driver.findElement(By.id("createLeadForm_firstName"));
				firstName.sendKeys(firstNameData);
				
				//Enter last name
				WebElement lastName = driver.findElement(By.id("createLeadForm_lastName"));
				lastName.sendKeys(lastNameData);
				
				//Enter first name local
				WebElement firstNameLocal = driver.findElement(By.id("createLeadForm_firstNameLocal"));
				firstNameLocal.sendKeys(localNameData);
				
				//Enter Department
				driver.findElement(By.id("createLeadForm_departmentName")).sendKeys(deptData);
				
				//Enter Description
				driver.findElement(By.id("createLeadForm_description")).sendKeys(descData);
				
				//Enter phone number
				driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(numberData);
				
				//Enter mail
				WebElement primaryEmail = driver.findElement(By.id("createLeadForm_primaryEmail"));
				primaryEmail.sendKeys(mailData);
				
				//Select state/province
				WebElement provinceElement = driver.findElement(By.id("createLeadForm_generalStateProvinceGeoId"));
				Select province=new Select(provinceElement);
				province.selectByVisibleText(provinceData);
				
				//click on create lead submit button
				WebElement submitCreateLeadButton = driver.findElement(By.className("smallSubmit"));
				submitCreateLeadButton.click();
			
				//get title of the landing page
				System.out.println("The title is "+driver.getTitle());
				

	}

}
