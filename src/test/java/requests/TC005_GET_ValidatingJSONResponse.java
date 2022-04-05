package requests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_ValidatingJSONResponse {
	
	@Test
	public void verifyResponseBody() 
	{
		// Specify base URI
		RestAssured.baseURI = "https://restapi.demoqa.com/utlities/weather/city";

		// Request object...
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/NYC");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response body is:" + responseBody);
		
		Assert.assertEquals(responseBody.contains("NYC"), true);
		
	}
}
