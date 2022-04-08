package allRequests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_GET_Request_PrintAllHeaders {
	
	@Test
	public void verifyAllHeaders() 
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
		
		Headers allHeaders=response.headers();  //capture all the headers from response
		
		for (Header header:allHeaders) 
		{
			System.out.println(header.getName()+ " ---> "+header.getValue());
		}

	}

}
