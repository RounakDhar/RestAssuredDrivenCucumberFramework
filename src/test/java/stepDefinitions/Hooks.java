package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforScenario() throws IOException {

		StepDefinition stepDefs = new StepDefinition();

		if (stepDefs.extractedPlaceIdString == null) {

			stepDefs.add_place_payload_with("Test", "French", "Asia");
			stepDefs.user_calls_with_http_request("AddPlaceAPI", "POST");
			stepDefs.verify_place_id_created_maps_to_using("Test", "GetPlaceAPI");

		}

	}

}
