package stepDefnitions;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario()
	{
		//execute this code only when place id is null
		//write a code that will give u place_id
		StepDefnitions step=new StepDefnitions();
		if(StepDefnitions.place_id==null)
		{
		step.add_place_payload("ravish", "kannada", "mathikere");
		step.user_calls_with_http_request("AddPlaceApi", "POST");
		step.verify_place_id_created_maps_to_using("ravish", "GetPlaceApi");
		}
	}
}
