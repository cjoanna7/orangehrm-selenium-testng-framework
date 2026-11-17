package OrangeHRMProject.tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import OrangeHRMProject.pageobjects.EmployeePage;
import OrangeHRMProject.pageobjects.TerminationReasonsPage;
import OrangeHRMProject.TestComponents.BaseTest;
import OrangeHRMProject.TestComponents.*;

public class HRTest extends BaseTest {
	@Test(dataProvider="getData", retryAnalyzer = Retry.class)
	public  void EmployeeTest(HashMap<String,String> input ) throws InterruptedException {
		
		EmployeePage employeePage = loginPage.loginApplication();
		String result = employeePage.searchEmployee(input.get("searchName"));
		Assert.assertTrue(result.contains("Record") && !result.contains("No Records"));

		String employeeID = employeePage.addEmployee(input.get("firstName"),
		        input.get("middleName"),
		        input.get("lastName"));
		String displayedEmployeeID = employeePage.newEmployeeValidation(input.get("firstName"));
		Assert.assertEquals(
		        displayedEmployeeID,
		        employeeID,
		        "Employee ID mismatch! Expected: " + employeeID + " but found: " + displayedEmployeeID);
		String noOfRecords =employeePage.employeeListPage(employeeID) ;
		Assert.assertEquals(noOfRecords, "(1) Record Found");
		
		TerminationReasonsPage terminationReasonsPage = employeePage.goToConfigurationDD();
		terminationReasonsPage.gotoTerminationReasons();
		terminationReasonsPage.addTerminationReason(input.get("terminationReason"));
		terminationReasonsPage.deleteTerminationReason(input.get("terminationReason"));	
		
	}
	
	@Test(dataProvider = "loginData")
	public void loginErrorValidation(String username, String password, String expectedMsg) {
	    String validationMessage = loginPage.loginError(username, password);
	    Assert.assertEquals(validationMessage, expectedMsg);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List <HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//OrangeHRMProject//data//EmployeeDetails.json");
		Object[][] result = new Object[data.size()][1];
	    for (int i = 0; i < data.size(); i++) {
	        result[i][0] = data.get(i);
	    }
	    return result;
	}
	
	@DataProvider
	public Object[][] loginData() {
	    return new Object[][] {
	        {"wrongusername", "wrongpassword", "Invalid credentials"}
	    };
	    
	} 

}
