package OrangeHRMProject.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "/reports/teststatus.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("OrangeHRMProject Results");
		reporter.config().setDocumentTitle("OrangeHRMProject Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Clara");
		return extent;
		
	}

}
