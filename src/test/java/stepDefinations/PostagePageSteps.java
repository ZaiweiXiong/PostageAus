package stepDefinations;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.PostagePage;
import base.Hook;

public class PostagePageSteps {
	
	private WebDriver driver;
	PostagePage postage;
	
	public PostagePageSteps() {
		
		this.driver = Hook.getDriver();
		postage = new PostagePage(driver);
		
	}
	
	
	@Given("^user go to Australia Post Calculate postage$")
	public void user_go_to_Australia_Post_Calculate_postage() throws Exception {
		
		driver.get(postage.url());
	  
	   
	}

	@When("^user Enter a source \"([^\"]*)\" and destination postcode \"([^\"]*)\" and selecting from the dropdown list$")
	public void user_Enter_a_source_and_destination_postcode_and_selecting_from_the_dropdown_list(String fromPostcode, String toPostcode) throws Exception {
	   
		//type departure post code and select one
		driver.findElement(postage.fromPostcode()).sendKeys(fromPostcode);
		Hook.waitElement(6,postage.selectFromOne());
		driver.findElement(postage.selectFromOne()).click();
		
		//type destination post code and select one 
		driver.findElement(postage.toPostcode()).sendKeys(toPostcode);
		Hook.waitElement(6, postage.selectToOne());
		driver.findElement(postage.selectToOne()).click();
		
		
	}

	@And("^user click on the Go button$")
	public void user_click_on_the_Go_button() throws Exception {
	   
		Hook.waitElement(6,postage.goButton());
		driver.findElement(postage.goButton()).click();
	   
	}
	
	@Then("^user type (.*),(.*),(.*) for calculate postage$")
	public void user_type_ss_for_calculate_postage(String size, String weight, String time) throws Exception {
	   
		//enter size,weight
		
		driver.findElement(postage.settingSizeAndWeight()).click();
		driver.findElement(postage.length()).sendKeys(size);
		driver.findElement(postage.width()).sendKeys(size);
		driver.findElement(postage.height()).sendKeys(size);
		driver.findElement(postage.weight()).sendKeys(weight);
		driver.findElement(postage.submitSizeAndWeight()).click();
		
		
		//enter the date
		driver.findElement(postage.setDate()).click();
		driver.findElement(postage.dateInput()).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '"+time+"')",
						  driver.findElement(postage.selectOneDate()));
		Hook.waitElement(6,postage.submitDate());
		driver.findElement(postage.submitDate());
		
	}
	
	@Then("^Validate the cost express post and parcel post$")
	public void validate_the_cost_express_post_and_parcel_post() throws Exception {
	    
		Assert.assertEquals(postage.getExpressPostPrice(), "$11.95");
		System.out.println("ExpressPostPrice=>"+postage.getExpressPostPrice());
		Assert.assertEquals(postage.getParcelpostPrice(), "$8.95");
		System.out.println("ParcelpostPrice=>"+postage.getParcelpostPrice());
	  
	}
	
	@Then("^Validate the cost express post and parcel post after setting size and date$")
	public void validate_the_cost_express_post_and_parcel_post_after_setting_size_and_date() throws Exception {
	    
		Assert.assertEquals(postage.getExpressPostPrice(), "$26.80");
		System.out.println("ExpressPostPrice with size and date =>"+postage.getExpressPostPrice());
		Assert.assertEquals(postage.getParcelpostPrice(), "$19.80");
		System.out.println("ParcelpostPrice with size and date =>"+postage.getParcelpostPrice());
	}


}
