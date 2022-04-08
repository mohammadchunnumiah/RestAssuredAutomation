package allRequests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request {

	@Test
	public void postRequest() {
		// Specify base URI
		RestAssured.baseURI = "https://reqres.in/api";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		// Payload
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Micheal");
		requestParams.put("job", "Tech Lead");

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(requestParams.toJSONString());  // attach above data to the request

		// Response object
		Response response = httpRequest.request(Method.POST, "/api/users");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response body is:" + responseBody);

		// status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is :" + statusCode);
		Assert.assertEquals(statusCode, 201);
		
		// success code validation
		String successCode=response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
		
		

//		String successCode = response.jsonPath().get("createdAt");
//		Assert.assertEquals(successCode, "2022-02-02T18:51:22.067Z");

	}
}
