package dataDrivenTesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestNG_DataProvider_AddNewEmployees {
	
@Test(dataProvider = "empdataprovider")
void postNewEmployees(String ename, String esal, String eage) 
{
	RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	
	RequestSpecification httpRequest = RestAssured.given();
	
	//here we created data which we can send along with the post request
	JSONObject requestParams = new JSONObject();
	requestParams.put("Name", "ename");
	requestParams.put("Salary", "eage");
	requestParams.put("Age", "40");
	

	//add a header starting the request body is a JSON
	httpRequest.header("Content-Type","application/json");
	
	//add the Json to the body of the request
	httpRequest.body(requestParams.toJSONString());

	//POST request
	Response response = httpRequest.request(Method.POST,"/create");
	
	//capture response body to perform validations
	String responseBody = response.getBody().asString();
	
	System.out.println("Response body is: " +responseBody);
	
	Assert.assertEquals(responseBody.contains("ename"), true);
	Assert.assertEquals(responseBody.contains("esal"), true);
	Assert.assertEquals(responseBody.contains("eage"), true);
	
	int statusCode = response.getStatusCode();
	Assert.assertEquals(statusCode, 200);	
}

@DataProvider(name = "empdataprovider")
String[][] getEmpData() throws IOException


{	// Read data from excel
	String path=System.getProperty("user.dir")+"src/test/ava/dataDrivenTesting/empdata.xlsx";
	
	int rownum=ExcelUtils.getRowCount(path, "Sheet1");
	int colcount=ExcelUtils.getCellCount(path, "Sheet1", 1);
	
	String empdata[][] = new String[rownum][colcount];

	for (int i = 1; i <= rownum; i++) 
	{
		for (int j = 0; j < colcount; j++) 
		{
			empdata[i - 1][j] = ExcelUtils.getCellData(path, "Sheet1", i, j);
		}
	}
	
	//TestNG data provider	
	//	String empdata[][] = { { "Halim", "3000", "30" }, { "Karim", "3200", "33" }, { "Yasin", "3500", "35" } };
	return (empdata);
}

}

//Youtube VDO ref:
//https://www.youtube.com/watch?v=brFtzAF1vMw&list=PLUDwpEzHYYLuMRzT6LFq4r8DwKZdcqHmY&index=3