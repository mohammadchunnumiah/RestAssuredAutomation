package requests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_Request {
	
	@Test
	public void getRequest() 
	{
		// Specify base URI
		RestAssured.baseURI = "https://maps.googleapis.com";

		// Request object...
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33...............");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response body is:" + responseBody);
		
		//validating headers
		String contentType=response.header("Content-Type");  //capture details of Content-Type header
		System.out.println("Content Type is: "+contentType);
		Assert.assertEquals(contentType, "application/xml; charset=UTF-8");
		
		String contentEncoding=response.header("Content-Encoding"); //capture details of Content-Encoding header
		System.out.println("Content Encoding is:" +contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
	}

}
