package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PostagePage {
	
	private WebDriver driver;
	
	public PostagePage (WebDriver driver) {
		
		this.driver = driver;
	}
	
	public String url() {
		
		String postageUrl = "https://auspost.com.au/parcels-mail/calculate-postage-delivery-times#/";
		return postageUrl;
	}
	
	public By fromPostcode() {
		By postCode = By.cssSelector("input[id='domFrom_value']");
		return postCode;
	}
	public By selectFromOne () {
		return By.xpath("//div[@id=\'domFrom_dropdown\']/div[3]/div/div");
	}
	
	public By toPostcode() {
		By postCode = By.cssSelector("input[id='domTo_value']");
		return postCode;
		
	}
	public By selectToOne() {
		return By.xpath("//div[@id=\'domTo_dropdown\']/div[3]/div/div");
	}
	
	public By goButton() {
		By go = By.cssSelector("button[id='submit-domestic']");
		return go;
	}
	
	public By settingSizeAndWeight() {
		
		return By.xpath("//a[@class='size-weight__enter-weight-link ng-scope']");
	}
	
	public By length() {
		
		return By.cssSelector("div[class='dimensions span-30p']>input");
	}
	public By width() {
		
		return By.cssSelector("input[name='widthInput']");
		
	}
	public By height() {
		return By.xpath("//input[@name='heightInput']");
	}
	
	public By weight() {
		
		return By.xpath("//input[@name='weightInput']");
	}
	
	public By submitSizeAndWeight() {
		return By.xpath("//button[@id='submit-set-dimensions']");
	}
	
	public By setDate() {
		return By.xpath("//a[@class='delivery-times__enter-time-link']");
	}
	
	public By dateInput() {
		
		return By.name("dateInput");
		
	}
	public By selectOneDate() {
		
		return By.cssSelector(".delivery-times__input-field");
		
	}
	
	public By submitDate() {
		
		return By.xpath("//button[@id='set-date']");
	}
	
	
	public String getExpressPostPrice() {
		
		
		String Price = driver.findElement(By.xpath("//div[3]/span[2]")).getText();
		
		return Price;
	}
	
	public String  getParcelpostPrice () {
		
		String Price = driver.findElement(By.xpath("//postage-service[2]/div/div/div/div/div[3]/span[2]")).getText();
		return Price;
	}
	

}
