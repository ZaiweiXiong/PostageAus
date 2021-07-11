package run.test.api;


import org.testng.annotations.Test;
import api.PostAgeApi;
import io.restassured.response.Response;
import resouces.APIResources;

public class TestPostageCost {
	
	String url = "https://digitalapi.auspost.com.au";
	APIResources path;
	Response res;
	PostAgeApi postage;
	
	@Test
	public void test_retrieve_post_parcel_sizes () {
		
		postage = new PostAgeApi(url);
		
		//Retrieve Australia Post standard parcel sizes
		path= APIResources.valueOf("retrieve_post_parcel_sizes");
		res = postage.retrieve_post_parcel_sizes(path.getPath());
		System.out.println("this is test postage cost!=>"+ res.prettyPrint());
		
	}
	
	@Test (dependsOnMethods = { "test_retrieve_post_parcel_sizes" })

	public void test_postage_and_extra_cover() {
		
		//Retrieve a list of available domestic postage services
		path = APIResources.valueOf("postage_and_extra_cover");
		res =postage.postage_and_extra_cover(path.getPath());
		System.out.println("test_postage_and_extra_cover =>"+ res.prettyPrint());
		
	}
	
	@Test  (dependsOnMethods = { "test_postage_and_extra_cover" })
	public void test_calculate_total_delivery_price() {
		
		// test delivery price
		path = APIResources.valueOf("calculate_total_delivery_price");
		res = postage.calculate_total_delivery_price(path.getPath());
		System.out.println("test_calculate_total_delivery_price =>"+ res.prettyPrint());
	}
	
	
	

}
