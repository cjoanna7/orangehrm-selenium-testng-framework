package OrangeHRMProject.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ErrorValidations {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		//List<WebElement> loginDetails=driver.findElements(By.xpath("//div[contains(@class,'orangehrm-demo-credentials')]//p"));
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("wrong username");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("wrong password");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		String validationMessage = driver.findElement(By.className("oxd-alert-content-text")).getText();
		Assert.assertEquals("Invalid credentials", validationMessage);
		driver.quit();

	}

}
