package employeeAPI_Framework;

import org.testng.annotations.BeforeClass;

import employeeAPI.TestBase;

import org.testng.Assert;
import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase{

	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		logger.info("*******TC001_Get_All_Employees**********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");

		Thread.sleep(3000);
	}

	@Test
	void checkResponseBody() {
		logger.info("*********Checking Response Body");

		String responseBody = response.getBody().asString();
		logger.info("Response Body==" + responseBody);
		Assert.assertTrue(responseBody != null);
	}

	@Test
	void CheckStatusCode() {
		logger.info("***Checking Status Code*****");
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Status code is ==> " + statusCode); // 200
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkResponseTime() {
		logger.info("****Checking Response Time*****");
		long responseTime = response.getTime(); // Getting status line
		logger.info("Response Time is ==> " + responseTime);

		if (responseTime > 2000)
			logger.warn("Response Time is greater than 2000");
		Assert.assertTrue(responseTime < 10000);
	}

	@Test
	void checkContentType() {
		logger.info("****Checking Content Type*****");
		String contentType = response.header("Content-Type"); // Getting Content-Type
		logger.info("Content type is ==> " + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	void checkServerType() {
		logger.info("****Checking Server Type*****");
		String serverType = response.header("Server"); // Getting Server-Type
		logger.info("Content type is ==> " + serverType);
		Assert.assertEquals(serverType, "nginx/1.14.1");
	}

	@Test
	void checkContentEncoding() {
		logger.info("****Checking Content Encoding*****");
		String contentEncoding = response.header("Content-Encoding"); // Getting Content Encoding
		logger.info("Content type is ==> " + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

	/*
	 * @Test void checkContentLength() {
	 * logger.info("****Checking Content Length*****"); String contentLength =
	 * response.header("Content-Length"); // Getting Content Length
	 * logger.info("Content type is ==> " + contentLength);
	 * 
	 * if (Integer.parseInt(Integer.parseInt(contentLength) < 100.00)) //parseInt
	 * getting error logger.warn("Content Length is less than 100");
	 * 
	 * Assert.assertTrue(Integer.parseInt(contentLength)<1500); }
	 */

	@Test
	void checkCookies() {
		logger.info("****Checking Cookies*****");
		String cookie = response.getCookie("PHPSESSID");
		// Assert.assertEquals(cookie, "lesuvsfslcmiee2bfrsgnijtg0");
	}

	@AfterClass
	void teadDown() {
		logger.info("****Finished TC001_Get_All_Employees*****");

	}

}
