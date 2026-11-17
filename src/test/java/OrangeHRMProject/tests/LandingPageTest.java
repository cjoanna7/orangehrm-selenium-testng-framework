package OrangeHRMProject.tests;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LandingPageTest {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		List<WebElement> loginDetails=driver.findElements(By.xpath("//div[contains(@class,'orangehrm-demo-credentials')]//p"));
		String username = loginDetails.get(0).getText().split(":")[1].trim();
		String password = loginDetails.get(1).getText().split(":")[1].trim();
		System.out.println(username);   
		System.out.println(password);
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys(username);
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		driver.findElement(By.linkText("PIM")).click();
		
		//searching for the employee pim page using name
		driver.findElement(By.xpath("//label[text()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input[@placeholder='Type for hints...']"))
		.sendKeys("Test");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'orangehrm-horizontal-padding')]//span[contains(@class,'oxd-text--span')]")).getText().contains("Found"));
		
		//adding an employee
		driver.findElement(By.xpath("//button[text()=' Add ']")).click();
		String firstName = "QA";
        String lastName  = "Selenium";
		driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName)	;
		driver.findElement(By.cssSelector("input[name='middleName']")).sendKeys("Automation");
		driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);
		By employeeIdLocator = By.xpath("//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input");
		String employeeID = driver.findElement(employeeIdLocator).getAttribute("value");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		//validating the employee id after creating on personal details Page which appears after creation
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIdLocator));
		String diaplyedEmployeeID = driver.findElement(employeeIdLocator).getAttribute("value");
		Assert.assertEquals(
				diaplyedEmployeeID,
				employeeID,
	            "Employee ID mismatch! Expected: " + employeeID + " but found: " + diaplyedEmployeeID
	        );
		//going back to the employee list page and searching for the added employee using employee id
		driver.findElement(By.linkText("Employee List")).click();
		//By employeeNameField =By.xpath("//label[text()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input[@placeholder='Type for hints...']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIdLocator));
		driver.findElement(employeeIdLocator).sendKeys(employeeID);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		String noOfRecords=driver.findElement(By.xpath("//div[contains(@class,'orangehrm-horizontal-padding')]//span[contains(@class,'oxd-text--span')]")).getText();
		Assert.assertEquals(noOfRecords, "(1) Record Found");
		driver.findElement(By.className("bi-chevron-down")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-topbar-body-nav-tab-link")));
		//navigating to termination reasons page
		List<WebElement> pimDropDown=driver.findElements(By.className("oxd-topbar-body-nav-tab-link"));
		for (WebElement c : pimDropDown)
		{
			if(c.getText().trim().equalsIgnoreCase("Termination Reasons"))
			{
				c.click();
				break;
			}
		}
		//adding termination reasons and submitting it
		driver.findElement(By.className("oxd-button--medium")).click();
		driver.findElement(By.xpath("//label[normalize-space()='Name']/ancestor::div[contains(@class,'oxd-input-group')]//input")).sendKeys("Testing");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		//deleting the termination reasons added
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-card")));
		List<WebElement> terminationReasonsDropDown=driver.findElements(By.className("oxd-table-card"));
		for (WebElement t : terminationReasonsDropDown)
		{
			if(t.getText().trim().equalsIgnoreCase("Testing"))
			{
				t.findElement(By.xpath(".//button[.//i[contains(@class,'bi-trash')]]")).click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[contains(@class,'orangehrm-modal-footer')]//button[normalize-space()='Yes, Delete']")).click();
		
		
	}

}
