package employeeAPI_Framework;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import employeeAPI.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.RestUtils;

public class TC003_Post_Employee_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	void createEmployee() throws InterruptedException {
		logger.info("******Started TC003_Post_Employee_Record**********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. We can add Key-Value
		// pairs using the put method
		// {"name":"John123", "salary":"123", "age":"33"}
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);

		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add teh Json the body of the request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.POST, "/create");

		Thread.sleep(5000);
	}

	@Test
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
	}

	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode(); // getting status code
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkStatusLine() {
		String statusLine = response.getStatusLine(); // getting status line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	void checkServerType() {
		String serverType = response.header("server");
		Assert.assertEquals(serverType, "nginx/1.14.1");
	}

	@Test
	void checkcontentEncoding() {
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
	}

	@AfterClass
	void teadDown() {
		logger.info("****Finished TC003_Post_Employee_Record*****");
	}

}
