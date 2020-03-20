package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GAddPlace;
import pojo.Location;

public class TestDataBuild {

	public GAddPlace addPlacePayload(String name,String language,String address)
	{
		GAddPlace p = new GAddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		return p;
	}
	
	public String deletePlacePayload(String place_id)
	{
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}\r\n" + 
				"";
	}
}
