package OrangeHRMProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import OrangeHRMProject.AbstractComponents.AbstractComponent;

public class TerminationReasonsPage extends AbstractComponent{

	WebDriver driver;
	
	public TerminationReasonsPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//@FindBy(className = "bi-chevron-down")
    //WebElement configurationDropDown;
	
	By dropDownWait = By.className("oxd-topbar-body-nav-tab-link");
	
	@FindBy(className="oxd-topbar-body-nav-tab-link") 
	List<WebElement> dropDownList;
	
	@FindBy(className = "oxd-button--medium")
    WebElement addBtn;
	
	@FindBy(xpath = "//label[normalize-space()='Name']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement reasonName;
	
	@FindBy(css = "button[type='submit']")
    WebElement saveBtn;
	
	By terminationReasonsTable = By.className("oxd-table-card");
	
	@FindBy(className = "oxd-table-card")
	List<WebElement> terminationReasonsList;
	
	By deleteBtn = By.xpath(".//button[.//i[contains(@class,'bi-trash')]]");
	
	@FindBy(xpath = "//div[contains(@class,'orangehrm-modal-footer')]//button[normalize-space()='Yes, Delete']")
	WebElement deleteConfirmation;
	
	public void gotoTerminationReasons()
	{	
		//navigating to termination reasons page
		//configurationDropDown.click();
		waitForElementToAppear(dropDownWait);
		List<WebElement> pimDropDown=dropDownList;
		for (WebElement c : pimDropDown)
		{
			if(c.getText().trim().equalsIgnoreCase("Termination Reasons"))
			{
				c.click();
				break;
			}
		}
		
	}
		
	public void addTerminationReason(String reason)
	{
		//adding termination reasons and submitting it
		addBtn.click();
		reasonName.sendKeys(reason);
		saveBtn.click();	
	}
	
	public void deleteTerminationReason(String reason)
	{
		//deleting the termination reasons added
		waitForElementToAppear(terminationReasonsTable);
		List<WebElement> terminationReasonsDropDown=terminationReasonsList;
		for (WebElement t : terminationReasonsDropDown)
		{
			if(t.getText().trim().equalsIgnoreCase(reason))
			{
				t.findElement(deleteBtn).click();
				break;
			}
		}
		deleteConfirmation.click();
	}

}
