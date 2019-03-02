package mercuryTour;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC001 {
	
	WebDriver driver;
	String baseURL="http://newtours.demoaut.com/";
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Naresh\\oxygen-workspace\\mercuryTour\\resources\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void verifyLogin() {
		driver.findElement(By.name("userName")).sendKeys("tutorial");
		driver.findElement(By.name("password")).sendKeys("tutorial");
		driver.findElement(By.name("login")).click();
		String expectedTitle="Find a Flight: Mercury Tours:";
		String actualTitle=driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		
		
	}

}
