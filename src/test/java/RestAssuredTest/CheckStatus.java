package RestAssuredTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map.Entry;
import io.restassured.response.Response;


public class CheckStatus {

	@Test
	public void checkstatus()
	{
		     
		        Response response = given()
                  
				//assignimg the values through parameters
				.pathParam("name", "worldwide")
				.when()
				//Retrieving the input from api using get call
				.get("https://istheapplestoredown.com/api/v1/status/{name}")
				.then()

				//validating the response
				.statusCode(200)  
				.assertThat().contentType("application/json")
				.extract().response();



		//Parsing the JSON response

		//Adding the response to HashMap
		HashMap<String,HashMap<String,String> > jsonResponse = response.jsonPath().get("$");

		//Using Iterator to iterate the entry values
		java.util.Iterator<Entry<String,HashMap<String,String> >> hmIterator = jsonResponse.entrySet().iterator();    

		//System.out.println(jsonResponse.size());

		//Printing the Response after parsing
		//System.out.println(jsonResponse);

		// String countrynames = response.jsonPath().getString("name");
		//System.out.println(countrynames);

		//Checking the Status value
		while (hmIterator.hasNext()) { 
			Entry<String, HashMap<String, String>> mapElement = hmIterator.next();

			HashMap<String,String> countryname_status =  mapElement.getValue(); 
			//   System.out.println(countryname_status.keySet());
			// System.out.println(mapElement.getKey() + "," + countryname_status.get("name")+","+countryname_status.get("status"));	
			if(countryname_status.get("status").contentEquals("y"))
			{
				System.out.println(countryname_status.get("name"));
				assert(1==0);
			}


		} 
	}


}