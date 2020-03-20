package resources;

public enum ApiResource {

	AddPlaceApi("/maps/api/place/add/json"),
	GetPlaceApi("/maps/api/place/get/json"),
	DeletePlaceApi("/maps/api/place/delete/json");

	private String resource;
	
	ApiResource(String resource) {
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	
	
	
}
