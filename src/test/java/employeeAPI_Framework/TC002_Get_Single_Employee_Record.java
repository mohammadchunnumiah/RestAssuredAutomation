package employeeAPI_Framework;

import org.testng.Assert;
import org.testng.annotations.*;

import employeeAPI.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	void getEmployeeData() throws InterruptedException {
		logger.info("******Started TC002_Get_Single_Employee_Record**********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/" + empID);

		Thread.sleep(3000);
	}

	@Test
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
	}

	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode(); // getting status code
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkResponseTime() {
		long responseTime = response.getTime(); // getting status time
		Assert.assertTrue(responseTime < 6000);
	}

	@Test
	void checkStatusLine() {
		String statusLine = response.getStatusLine(); // getting status line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {
		String contentType = response.header("Content-Type"); // getting Content-Type
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	void serverType() {
		String serverType = response.header("Server"); // getting Server Type
		Assert.assertEquals(serverType, "nginx/1.14.1");
	}

	@Test
	void checkContentLength() {
		String contentLength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength) < 1500);
	}

	@AfterClass
	void teadDown() {
		logger.info("****Finished TC002_Get_Single_Employee_Record*****");
	}

}

//YouTube VDO ref:  https://www.youtube.com/watch?v=QI8vvNCiaYI&list=PLUDwpEzHYYLuMRzT6LFq4r8DwKZdcqHmY&index=4
