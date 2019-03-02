package mercuryTour;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MercuryTour_LoginTest {

	private WebDriver driver;
	private String baseurl;
	ExtentReports extent;
	ExtentTest test;
	ExtentHtmlReporter htmlReports;
	String fileName = System.getProperty("user.dir") + "/test-output/HtmlTestResult.html";

	@BeforeClass
	public void setUp() {
		htmlReports = new ExtentHtmlReporter(fileName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReports);
		htmlReports.config().setReportName("Regression Testing");
		htmlReports.config().setTheme(Theme.STANDARD);
		htmlReports.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Environment", "QA-AUT");
		extent.setSystemInfo("Java", "8.0 version");		
	}

	@Test(priority = 0)
	public void openBrowser() {
		test = extent.createTest("TC_1 = Open Chrome Browser using Chrome driver");
		test.log(Status.INFO, "Test execution is Started");
		System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		test.log(Status.INFO, "Chrome Browser is Open");
	}

	@Test(priority = 1)
	public void OpenAutUrl() {
		test = extent.createTest("TC_2 = Open AUT url");
		baseurl = "http://newtours.demoaut.com/";
		driver.get(baseurl);
		test.log(Status.PASS, "Application under Test url is open");
	}

	@Test(priority = 2)
	public void verifyPageTitle() {
		test = extent.createTest("TC_3 = verify proper url is open or not");
		String actualPageTitle = driver.getTitle();
		Assert.assertTrue(actualPageTitle.equals("Welcome: Mercury Tour"));
	}

	@AfterClass
	public void cleanUp() {
		extent.flush();
		driver.close();
	}
	

	@AfterMethod
	public void checkResults(ITestResult testResults) {
		if (testResults.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test case is failed because of below problem");
			test.log(Status.FAIL, testResults.getThrowable());
		} else if (testResults.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test case is pass");
		} else if (testResults.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, testResults.getThrowable());

		}
	}

}
