package New_Project.Project_Assignment;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.hamcrest.core.IsEqual;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class TestCases {
	ResponseSpecification res;
	@BeforeClass
	public void serSpecification() {
		res = RestAssured.expect();
		res.statusLine("HTTP/1.1 200 OK");
		res.contentType(ContentType.JSON);
		ExtentReportManager.createReport();
		
	}
	
	@Test(description = "Creating the token", testName = "testCreatetoken")
	public void testCreatetoken() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://restful-booker.herokuapp.com");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Body passed","username=admin and password=password123");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","auth");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","username");
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		HashMap<String,String> params = new HashMap<String, String>();
		params.put("username", "admin");
		params.put("password", "password123");
		given().when().contentType("application/json").body(params).post("auth").then().statusCode(200);
	}
	
	
	
	@Test(description="Getting the booking IDs", testName = "testGetBookingIds")
	public void testGetBookingIds() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://restful-booker.herokuapp.com");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","booking");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","page");
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		given().when().get("booking").then().statusCode(200);
		
	}
	
	@Test(description="Getting the booking", testName = "testGetBooking")
	public void testGetBooking() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://restful-booker.herokuapp.com");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","/booking/:id");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","bookingid");
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		given().when().contentType("application/json").get("/booking/1").then().assertThat().body("firstname", IsEqual.equalTo("Susan"));
	}
	
	@SuppressWarnings("unchecked")
	@Test(description = "Creating the Booking", testName = "testCreateBooking")
	public void testCreatebooking() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI","https://restful-booker.herokuapp.com");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Body passed"," ");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","/booking");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","booking.additionalneeds");
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		JSONObject file = new JSONObject();
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2018-01-01");
		bookingdates.put("checkout", "2019-01-01");
	    file.put("firstname", "Jim");
	    file.put("lastname", "Brown");
	    file.put("totalprice", 111);
	    file.put("depositpaid", true);
	    file.put("bookingdates",bookingdates);
	    file.put("additionalneeds", "Breakfast");
		given().when().contentType("application/json").body(file).post("/booking").then().assertThat().body("booking.additionalneeds", IsEqual.equalTo("Breakfast"));
	}
	
	@Test(description="Checking the health", testName = "HealthCheck")
	public void HealthCheck() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://restful-booker.herokuapp.com");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","ping");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","page");
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		given().when().get("ping").then().statusCode(201);
		
	}
	
	@AfterClass
	public void closeReport() {
		ExtentReportManager.reports.flush();
		ExtentReportManager.reports.close();
	}

}
