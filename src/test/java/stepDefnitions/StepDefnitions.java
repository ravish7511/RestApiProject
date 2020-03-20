package stepDefnitions;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GAddPlace;
import pojo.Location;
import resources.ApiResource;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefnitions extends Utils {
	RequestSpecification res;
	Response response;
	static String place_id;

	TestDataBuild td = new TestDataBuild();

	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) {

		res = given().relaxedHTTPSValidation().spec(requestSpecification())
				.body(td.addPlacePayload(name, language, address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		ApiResource apiVal = ApiResource.valueOf(resource);
		System.out.println(apiVal.getResource());

		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(apiVal.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(apiVal.getResource());

	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String aKeyValue, String eKeyValue) {

		assertEquals(getJsonPath(response, aKeyValue), eKeyValue);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) {
		 place_id = getJsonPath(response, "place_id");
		res = given().relaxedHTTPSValidation().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
		
	}
	
	@Given("DeletePlace payload")
	public void deleteplace_payload() {
	    
		res=given().relaxedHTTPSValidation().spec(requestSpecification()).body(td.deletePlacePayload(place_id));
	}
}
