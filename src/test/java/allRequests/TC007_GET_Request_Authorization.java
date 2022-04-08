package allRequests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_GET_Request_Authorization {

	@Test
	public void getRequest_Authentication() 
	{
		// Specify base URI
		RestAssured.baseURI = "https://reqres.in/api";
		
		//Basic authentication
		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		authscheme.setUserName("username");
		authscheme.setPassword("password");
		
		RestAssured.authentication=authscheme;
			

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/");

		// status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is" + statusCode);
		Assert.assertEquals(statusCode, 200);

		// status line verification
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response body is:" + responseBody);
	}
}






//Youtube video ref are as below: 
	//VDO-1:  https://www.youtube.com/watch?v=yDdBOspPp_c&list=PLUDwpEzHYYLuMRzT6LFq4r8DwKZdcqHmY 
	//VDO-2: https://www.youtube.com/watch?v=yDdBOspPp_c
	//VDO-3: https://www.youtube.com/watch?v=brFtzAF1vMw
	//VDO-4: https://www.youtube.com/watch?v=QI8vvNCiaYI
	//VDO-5: https://www.youtube.com/watch?v=BWD8woHxCVM&list=PLUDwpEzHYYLuMRzT6LFq4r8DwKZdcqHmY&index=5
