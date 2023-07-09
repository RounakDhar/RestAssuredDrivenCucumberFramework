package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {

	ResponseSpecification resSpec;
	RequestSpecification givenResponse;
	Response whenResponse;
	static String extractedPlaceIdString;

	TestDataBuild dataBuild = new TestDataBuild();

	// --1st Scenario Given Statement--

	@Given("AddPlace Payload")
	public void add_place_payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		// throw new io.cucumber.java.PendingException();

		givenResponse = given().spec(requestSpecification()).body(dataBuild.addPlacePayload(null, null, null));

	}

	@When("User Calls {string} with {string} HTTP Request")
	public void user_calls_with_http_request(String resource, String HTTPMethods) {
		// Write code here that turns the phrase above into concrete actions
		// throw new io.cucumber.java.PendingException();

		APIResources resourceAPI = APIResources.valueOf(resource);
		String extractedResources = resourceAPI.getResource();
		System.out.println(extractedResources);

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (HTTPMethods.equalsIgnoreCase("POST")) {

			whenResponse = givenResponse.when().post(extractedResources);

		}

		else if (HTTPMethods.equalsIgnoreCase("GET")) {

			whenResponse = givenResponse.when().get(extractedResources);

		}

		System.out.println("The HTTP MEthods used is: " + HTTPMethods);

	}

	@Then("the API call got Success with Status Code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		// throw new io.cucumber.java.PendingException();

		assertEquals(whenResponse.getStatusCode(), 200);

	}

	@And("{string} in Response Body is {string}")
	public void in_response_body_is(String keyValue, String expctedValue) {
		// Write code here that turns the phrase above into concrete actions
		// throw new io.cucumber.java.PendingException();

		assertEquals(getJsonPath(whenResponse, keyValue), expctedValue);

	}

	// --Scenario Outline Given Statement--

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String Name, String Language, String Address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		// throw new io.cucumber.java.PendingException();

		givenResponse = given().spec(requestSpecification()).body(dataBuild.addPlacePayload(Name, Language, Address));

	}

	@And("Verify place_Id Created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		// throw new io.cucumber.java.PendingException();

		extractedPlaceIdString = getJsonPath(whenResponse, "place_Id");
		System.out.println(extractedPlaceIdString);

		givenResponse = given().spec(requestSpecification()).queryParam("place_Id", extractedPlaceIdString);

		user_calls_with_http_request(resource, "GET");

		String actualName = getJsonPath(whenResponse, "Name");
		assertEquals(actualName, expectedName);

	}

	// --2nd Scenario Given Statement--

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		givenResponse = given().spec(requestSpecification())
				.body(dataBuild.deletePlacePayload(extractedPlaceIdString));
	}

}
