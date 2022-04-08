package allRequests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_GET_ExtractValuesOfEachNodeInJSON {

	@Test
	public void verifyAllNodes() 
	{
		// Specify base URI
		RestAssured.baseURI = "https://restapi.demoqa.com/utlities/weather/city";

		// Request object...
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/NYC");

		JsonPath jsonpath = response.jsonPath();

		System.out.println(jsonpath.get("node's name"));
		System.out.println(jsonpath.get("node's name"));
		System.out.println(jsonpath.get("node's name"));
		System.out.println(jsonpath.get("node's name"));
		
		Assert.assertEquals(jsonpath.get("node's name"), "expected value");

	}
}
