package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification req;
	ResponseSpecification res;
	public RequestSpecification requestSpecification() {
		
		if(req==null)
		{
		PrintStream log=null;
		try {
			log = new PrintStream(new FileOutputStream("logging.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		 return req;
		}
		return req;
	}
	
	public ResponseSpecification responseSpecification()
	{
		 res = new ResponseSpecBuilder()
					.expectStatusCode(200).expectContentType(ContentType.JSON)
					.build();
		 return res;
	}
	
	public static String getGlobalValue(String key)
	{
		String url="";
		Properties pro=new Properties();
		try {
			FileInputStream fis=new FileInputStream("E:\\restwk\\Apiproject\\src\\test\\java\\resources\\global.properties");
			pro.load(fis);
			 url = pro.getProperty(key);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

	public String getJsonPath(Response response,String key)
	{
		String rep = response.asString();
		JsonPath js = new JsonPath(rep);
		return js.get(key).toString();
	}
}
