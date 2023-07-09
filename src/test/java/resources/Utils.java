package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	RequestSpecification reqSpec;

	public RequestSpecification requestSpecification() throws IOException {

		if (reqSpec == null)

		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseUrl"))
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();

			return reqSpec;
		}

		return reqSpec;

	}

	public static String getGlobalProperties(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");

		prop.load(fis);
		String extractedKeyString = prop.getProperty(key);

		return extractedKeyString;
	}
	
	public static String getJsonPath(Response whenResponse, String key) {
		
		String respArg1 = whenResponse.asString();
		JsonPath json = new JsonPath(respArg1);
		
		String extractedKeyValueString =  json.get(key).toString();
		//System.out.println(extractedKeyValueString);
		
		return extractedKeyValueString;
	}

}
