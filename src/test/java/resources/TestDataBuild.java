package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String Name, String Language, String Address) {
		
		AddPlace aplace = new AddPlace();

		aplace.setAccuracy(50);
		aplace.setAddress(Address);
		aplace.setLanguage(Language);
		aplace.setPhone_number("(+91) 983 893 3937");
		aplace.setWebsite("http://google.com");
		aplace.setName(Name);

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		aplace.setTypes(myList);

		Location myLocations = new Location();
		myLocations.setLat(-38.383494);
		myLocations.setLng(33.427362);
		aplace.setLocation(myLocations);
		
		return aplace;
		
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\\r\\n    \\\"place_id\\\":\\\"\"+placeId+\"\\\"\\r\\n}";
		
	}

}
