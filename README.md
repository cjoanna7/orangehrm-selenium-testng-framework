OrangeHRM Selenium TestNG Framework

UI test automation framework for the OrangeHRM demo application, built with Java, Selenium WebDriver, TestNG and Maven using the Page Object Model.

Tech Stack
Area	Tool
Language	Java 17
Browser automation	Selenium WebDriver 4
Test runner	TestNG
Build tool	Maven
Reporting	ExtentReports
Design pattern	Page Object Model (PageFactory)
Features
Page Object Model with a reusable AbstractComponent base class for common waits and actions
BaseTest that handles driver setup and teardown
Cross-browser support (Chrome and Firefox), selected at run time with -Dbrowser
Retry analyzer to re-run a failed test once
TestNG listeners for reporting and screenshots on failure
ExtentReports HTML report
Config values kept in GlobalData.properties, with command-line overrides
Project Structure
.
├── pom.xml
├── testng.xml
└── src
    ├── main/java
    │   └── ...                       # Framework code (page objects, abstract components, utilities)
    └── test
        ├── java
        │   └── OrangeHRMProject
        │       ├── TestComponents    # BaseTest, Listeners, Retry
        │       ├── pageobjects       # Page classes (login, landing, employee, ...)
        │       ├── data              # Test data
        │       └── tests             # Test classes (LandingPageTest, HRTest, ErrorValidations)
        └── resources
            └── GlobalData.properties # Default browser and settings

Adjust this tree so it matches your final layout.

Prerequisites
JDK 17 or higher
Maven 3.8+
Google Chrome and/or Firefox installed

Selenium 4.6+ downloads the matching browser driver automatically, so no manual driver setup is needed.

How to Run

Clone the repository:

bash
git clone https://github.com/<your-username>/orangehrm-selenium-testng-framework.git
cd orangehrm-selenium-testng-framework

Run all tests with the default browser from GlobalData.properties:

bash
mvn clean test

Choose a browser from the command line:

bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox

The -Dbrowser value overrides the one in GlobalData.properties.

Reports

After a run, open the ExtentReports HTML file in the reports folder (for example reports/teststatus.html) in a browser. It shows each test's status, timing, error details and a screenshot for failures.

<!-- Add a screenshot of your report here: ![Test report](docs/report.png) -->
Notes
Tests run against the public OrangeHRM demo site, which is shared and reset periodically, so record counts and data can change between runs.
Failed tests are retried once by the retry analyzer. Check the report for tests that only pass on retry, since they point to timing problems.
Author

Clara