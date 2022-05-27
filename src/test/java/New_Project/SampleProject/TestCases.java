package New_Project.SampleProject;

import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class TestCases {

	ResponseSpecification res;
	@BeforeClass
	public void serSpecification() {
		res = RestAssured.expect();
		res.statusLine("HTTP/1.1 200 OK");
		res.contentType(ContentType.JSON);
		ExtentReportManager.createReport();
		
	}
	
	@Test(description="Fetching the users", testName = "testFetchUser")
	public void testFetchUser() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/users?delay=3");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","page");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users?delay=3").then().statusCode(200);
		
	}
	
	@Test(description = "Creating the user", testName = "testCreateUser")
	public void testCreateUser() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Body passed","name=morpheus and job=leader");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/users");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","name");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<String, String>();
		params.put("name", "morpheus");
		params.put("job", "leader");
		given().when().contentType("application/json").body(params).post("api/users").then().statusCode(201);
	}
	
	@Test(description = "Testing the single user", testName = "testSingleUser")
	public void testSingleUser() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/users/2");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","page");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users/2").then().statusCode(200);
	}
	
	@Test(description = "testing whether the login is successful or not", testName = "testLoginSuccessful")
	public void testLoginSuccessful() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Body passed","email=eve.holt@reqres.in and password=cityslicka");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/login");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","name");
		HashMap<String,String> params = new HashMap<String, String>();
		params.put("email", "eve.holt@reqres.in");
		params.put("password", "cityslicka");
		given().when().contentType("application/json").body(params).post("api/login").then().statusCode(200);
	}
	
	@AfterClass
	public void closeReport() {
		ExtentReportManager.reports.flush();
		ExtentReportManager.reports.close();
	}
}
