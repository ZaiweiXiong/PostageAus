package api;

import static io.restassured.RestAssured.given;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class PostAgeApi {
	
	String url ;
	String key = "7f4e5bc4-ed30-4543-a1bf-a1a26522d889";
	
	RequestSpecification req;
	ResponseSpecification resspec ;
	
	public PostAgeApi (String url) {
		
		this.url = url;
		
		req =new RequestSpecBuilder().addHeader("AUTH-KEY",key)
				.setBaseUri(url).setAccept(ContentType.JSON).build();
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
	}
	
	
	public Response retrieve_post_parcel_sizes(String path) {
		
		return given().spec(req).
				when().get(path).
				then().spec(resspec).extract().response();
	}
	
	public Response postage_and_extra_cover(String path) {
		
		JSONObject jo = new JSONObject();
		jo.put("from_postcode", "2000");
		jo.put("to_postcode", "3000");
		jo.put("length", "22");
		jo.put("width", "16");
		jo.put("height", "7");
		jo.put("weight", "1.5");
		
		return given().spec(req).params(jo).
				when().get(path).
				then().spec(resspec).extract().response();
		
		
	}
	
	public Response calculate_total_delivery_price(String path) {
		
		JSONObject jo = new JSONObject();
		jo.put("from_postcode", "2000");
		jo.put("to_postcode", "3000");
		jo.put("length", "22");
		jo.put("width", "16");
		jo.put("height", "7");
		jo.put("weight", "1.5");
		jo.put("service_code", "AUS_PARCEL_REGULAR");
		
		return given().spec(req).params(jo).
				when().get(path).
				then().spec(resspec).extract().response();
		
	}
	
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
