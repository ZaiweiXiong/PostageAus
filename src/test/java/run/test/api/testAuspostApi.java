package run.test.api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class TestAuspostApi {
	
	private static RequestSpecification requestSpec;
	
	
	@BeforeClass
	public void testApiSetup() 
    {
		
		RequestSpecBuilder builder = new RequestSpecBuilder();
		String key="7f4e5bc4-ed30-4543-a1bf-a1a26522d889";
		builder.addHeader("AUTH-KEY", key);
		requestSpec = builder.build();
		System.out.println("Api test before class");
	}
	
	@Test(groups="API")
	@Parameters({ "url_api","from_postcode","to_postcode",
		          "length","width","height","weight","service_code_regular"})
	public void test_calculate_total_delivery_price_with_regual_api(
														String url_api,String fp,String tp,
			 											String length,String width, String height,
			 											String weight,String scr)
	{
		System.out.println("Calculate total delivery price with regular");
		
		RestAssured.basePath=url_api+
				 "/postage/parcel/domestic/calculate.json?"
				 										  +"from_postcode="+fp
				 										  + "&to_postcode="+tp
				 										  + "&length="+length
				 										  + "&width="+width
				 										  + "&height="+height
				 										  + "&weight="+weight
				 										  + "&service_code="+scr;
	    
		Response res = 
				given().spec(requestSpec).log().all()
				      .contentType(ContentType.JSON)
				.when()
				     .get(RestAssured.basePath)
				.then()
				     .statusCode(200)
				     .statusLine("HTTP/1.1 200 OK")
				     .extract().response();
		
		
		String responseBody = res.asString();
		Assert.assertTrue(responseBody.contains("postage_result"));
		JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();
		Assert.assertTrue(jsonObject.isJsonObject());
		jsonObject = new JsonParser().parse(jsonObject.get("postage_result").toString()).getAsJsonObject();
		Assert.assertTrue(jsonObject.get("total_cost").getAsString().equals("26.90"));
		System.out.println("regular cost=>"+jsonObject.get("costs"));
	}
	
	@Test(groups="API")
	@Parameters({ "url_api","from_postcode","to_postcode",
		          "length","width","height","weight","service_code_express"})
	public void test_calculate_total_delivery_price_with_express_api(String url_api,String fp,String tp,
																	 String length,String width, String height,
																	 String weight,String sce) 
	 {
		
		System.out.println("Calculate total delivery price with express");
		
		RestAssured.basePath=url_api+
				 "/postage/parcel/domestic/calculate.json?"
				 										  +"from_postcode="+fp
				 										  + "&to_postcode="+tp
				 										  + "&length="+length
				 										  + "&width="+width
				 										  + "&height="+height
				 										  + "&weight="+weight
				 										  + "&service_code="+sce;
	    
		Response res = 
				given().spec(requestSpec).log().all()
				      .contentType(ContentType.JSON)
				.when()
				     .get(RestAssured.basePath)
				.then()
				     .statusCode(200)
				     .statusLine("HTTP/1.1 200 OK")
				     .extract().response();
		
		
		String responseBody = res.asString();
		Assert.assertTrue(responseBody.contains("postage_result"));
		JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();
		Assert.assertTrue(jsonObject.isJsonObject());
		jsonObject = new JsonParser().parse(jsonObject.get("postage_result").toString()).getAsJsonObject();
		Assert.assertTrue(jsonObject.get("total_cost").getAsString().equals("59.70"));
		System.out.println("express cost=>"+jsonObject.get("costs"));
		
	}

}
