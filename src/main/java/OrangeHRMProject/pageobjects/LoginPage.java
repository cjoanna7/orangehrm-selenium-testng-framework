package OrangeHRMProject.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import OrangeHRMProject.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'orangehrm-demo-credentials')]//p") 
	List<WebElement> loginDetails;
	
	@FindBy(css = "input[name='username']")
    WebElement userEmail;

    @FindBy(css = "input[name='password']")
    WebElement userPassword;

    @FindBy(css = "button[type='submit']")
    WebElement loginBtn;
    
    @FindBy(className = "oxd-alert-content-text")
    WebElement validationText;
	
	
    public EmployeePage loginApplication() {
        String username = loginDetails.get(0).getText().split(":")[1].trim();
        String password = loginDetails.get(1).getText().split(":")[1].trim();
        userEmail.sendKeys(username);
        userPassword.sendKeys(password);
        loginBtn.click();
        EmployeePage employeePage = new EmployeePage(driver);
        return employeePage;
    }
    
    public String loginError(String username,String password)
    {
    	userEmail.sendKeys(username);
        userPassword.sendKeys(password);
        loginBtn.click();
        waitForWebElementToAppear(validationText);
        String validationMessage = validationText.getText();
        return validationMessage;
    }
	
    public void goTo()
	{
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

}
