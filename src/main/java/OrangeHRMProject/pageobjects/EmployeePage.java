package OrangeHRMProject.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import OrangeHRMProject.AbstractComponents.AbstractComponent;

public class EmployeePage extends AbstractComponent{
	WebDriver driver;

	public EmployeePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "PIM")
    WebElement pimBtn;

    @FindBy(xpath = "//label[text()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input[@placeholder='Type for hints...']")
    WebElement empSearch;

    @FindBy(css = "button[type='submit']")
    WebElement empSearchBtn;
    
    @FindBy(xpath = "//button[text()=' Add ']")
    WebElement addEmpBtn;

    @FindBy(css = "input[name='firstName']")
    WebElement firstNameField;
    
    @FindBy(css = "input[name='middleName']")
    WebElement middleNameField;
    
    @FindBy(css = "input[name='lastName']")
    WebElement lastNameField;

    @FindBy(css = "button[type='submit']")
    WebElement submitBtn;
    
    @FindBy(className = "oxd-select-text-input")
    WebElement employeeSaveWait;
    
    @FindBy(linkText = "Employee List")
    WebElement employeeList;
    
    @FindBy(css = "button[type='submit']")
    WebElement employeeListSubmitBtn;
    
    @FindBy(className = "oxd-form-loader")
    WebElement invisibilityWait;
    
    @FindBy(xpath = "//div[contains(@class,'orangehrm-horizontal-padding')]//span[contains(@class,'oxd-text--span')]")
    WebElement result;
    
    @FindBy(xpath="//div[contains(@class,'orangehrm-horizontal-padding')]//span[contains(@class,'oxd-text--span')]")
    WebElement resultInfo;

    By employeeIdLocator = By.xpath(
            "//label[normalize-space()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input");

    @FindBy(className = "bi-chevron-down")
    WebElement configurationDropDown;
    
	public String searchEmployee(String name)
	{
		
		pimBtn.click();
		empSearch.sendKeys(name);
		empSearchBtn.click();
		waitForWebElementToAppear(resultInfo);
        return resultInfo.getText();	
	
	}
	
	public String addEmployee(String firstName, String middleName, String lastName)
	{
		
		addEmpBtn.click();
		firstNameField.sendKeys(firstName)	;
		middleNameField.sendKeys(middleName);
		lastNameField.sendKeys(lastName);
        String employeeID = driver.findElement(employeeIdLocator).getAttribute("value");
		submitBtn.click();
		return employeeID;	
		
	}
	
	public String newEmployeeValidation(String expectedFirstName)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		waitForUrlContains("viewPersonalDetails");
	    wait.until(d -> expectedFirstName.equals(
	            d.findElement(By.name("firstName")).getAttribute("value")));
	    wait.until(d -> {
	        String v = d.findElement(employeeIdLocator).getAttribute("value");
	        return v != null && !v.isEmpty();
	    });
	    
		return driver.findElement(employeeIdLocator).getAttribute("value");
	    
	    
	}
	
	public String employeeListPage(String employeeID )
	{
		
		employeeList.click();
		waitForElementToAppear(employeeIdLocator);
		driver.findElement(employeeIdLocator).sendKeys(employeeID);
		employeeListSubmitBtn.click();
		waitForElementToDisappear(invisibilityWait);
		waitForWebElementToAppear(result);
		String noOfRecords=result.getText();
		return noOfRecords;
		
	}
	
	public TerminationReasonsPage goToConfigurationDD()
	{
		
		configurationDropDown.click();
		return new TerminationReasonsPage(driver);
		
	}
	

}
