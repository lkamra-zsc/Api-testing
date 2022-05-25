package New_Project.SampleProject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
		
	}
	
	@Test
	public void testFetchUser() {
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users?delay=3").then().statusCode(200);
	}
	
	@Test
	public void testCreateUser() {
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<String, String>();
		params.put("name", "morpheus");
		params.put("job", "leader");
		given().when().contentType("application/json").body(params).post("api/users").then().statusCode(201);
	}
	
	@Test
	public void testSingleUser() {
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users/2").then().statusCode(200);
	}
	
	@Test
	public void testLoginSuccessful() {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put("email", "eve.holt@reqres.in");
		params.put("password", "cityslicka");
		given().when().contentType("application/json").body(params).post("api/login").then().statusCode(200);
	}
}
