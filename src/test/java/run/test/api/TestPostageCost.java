package run.test.api;

import static io.restassured.RestAssured.given;
import java.util.List;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSONObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resouces.APIResources;

public class TestPostageCost {
	
	String url = "https://digitalapi.auspost.com.au";
	String key = "7f4e5bc4-ed30-4543-a1bf-a1a26522d889";
	String path= "";
	APIResources p;
	
	Response res;
	
	RequestSpecification req =new RequestSpecBuilder().addHeader("AUTH-KEY",key)
			.setBaseUri(url).setAccept(ContentType.JSON).build();
	
	ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();
	
	
	
	@Test
	public void test_retrieve_post_parcel_sizes () {
		
		//Retrieve Australia Post standard parcel sizes
		p= APIResources.valueOf("retrieve_post_parcel_sizes");
		res =given().spec(req).
						when().get(p.getPath()).
						then().spec(resspec).extract().response();
		
		System.out.println("this is test postage cost!=>"+ res.asString());
		
	}
	
	@Test
	public void test_postage_and_extra_cover() {
		
		//Retrieve a list of available domestic postage services
		p = APIResources.valueOf("postage_and_extra_cover");
		path = p.getPath();
		
		
		JSONObject jo = new JSONObject();
		jo.put("from_postcode", "2000");
		jo.put("to_postcode", "3000");
		jo.put("length", "22");
		jo.put("width", "16");
		jo.put("height", "7");
		jo.put("weight", "1.5");
		
		res =given().spec(req).params(jo).
				when().get(path).
				then().spec(resspec).extract().response();
		
		System.out.println("test_postage_and_extra_cover =>"+ res.asString());
		
	}
	
	@Test
	public void test_calculate_total_delivery_price() {
		
		
		p = APIResources.valueOf("calculate_total_delivery_price");
		
		JSONObject jo = new JSONObject();
		jo.put("from_postcode", "2000");
		jo.put("to_postcode", "3000");
		jo.put("length", "22");
		jo.put("width", "16");
		jo.put("height", "7");
		jo.put("weight", "1.5");
		jo.put("service_code", "AUS_PARCEL_REGULAR");
		
		res =given().spec(req).params(jo).
				when().get(p.getPath()).
				then().spec(resspec).extract().response();
		
		System.out.println("test_calculate_total_delivery_price =>"+ res.asString());
	}
	
	@Test(enabled=false)
	public void test_country () {
		
		path = "/postage/country.AFGHANISTAN";
		
		res =given().spec(req).
				when().get(path).
				then().spec(resspec).extract().response();
		
		System.out.println("test country =>"+ res.asString());
	}
	
	
	
	@Test(enabled=false)
	public void test_postage_cost() {
		
		
		
		System.out.println("this is test postage cost!");
		
		
		RestAssured.baseURI = "https://digitalapi.auspost.com.au";
		
		String path = "/postage/parcel/domestic/size.json";
		
		String key="7f4e5bc4-ed30-4543-a1bf-a1a26522d889";
		
		Response res = given().header("AUTH-KEY",key)
		
		.when()
	     .get(path)
	    .then()
	     .statusCode(200)
	     .statusLine("HTTP/1.1 200 OK")
	     .extract().response();
		
		int i = res.jsonPath().getList("sizes.size").size();
		
		List<Object> l = res.jsonPath().getList("sizes.size");
		
		JsonPath jp = new JsonPath(res.asString());
		jp.getString("sizes.size");
		
		System.out.println("length =>"+i);
		
		System.out.println(res.asString());
		
		
		
	}
	
	

}
