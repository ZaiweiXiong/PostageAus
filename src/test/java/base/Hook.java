package base;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {
	
private static WebDriver driver;
static String driverPath = "./drivers/";
	
    Scenario scenario;

	@Before ("@post")
	public void setUp(Scenario s)
	{
		
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver");
		driver = new ChromeDriver();
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String url = "https://www.google.com";
		driver.get(url);
		
	}
	
	@After
	public void tearDown()
	{
	
		driver.close();
		driver.quit();
		
	}
	
	public static WebDriver getDriver()
	
	{
		return driver;
	}
	
	public static WebElement waitElement(int secs,By x){
		
		
		WebDriverWait wait = new WebDriverWait(driver, secs);
		WebElement element = wait
							 .until(ExpectedConditions.elementToBeClickable(x));
		return element;
	}
	

}
