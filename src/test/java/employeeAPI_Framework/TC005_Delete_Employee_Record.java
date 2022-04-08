package employeeAPI_Framework;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import employeeAPI.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.RestUtils;

public class TC005_Delete_Employee_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	void deleteEmployee() throws InterruptedException {
		logger.info("******Started TC005_Delete_Employee_Record**********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/employee");

		// first get the JsonPath objet instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// capture ID
		String empID = jsonPathEvaluator.get("[0].id");
		response = httpRequest.request(Method.DELETE, "/delete/" + empID); // Pass ID to delete the record

		Thread.sleep(3000);
	}

	@Test
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
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
		logger.info("****Finished TC005_Delete_Employee_Record*****");
	}

}
