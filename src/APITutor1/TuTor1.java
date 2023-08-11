package APITutor1;

// until section 5 end;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payLoad.AddPlacepackage;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;  //equalTo() comes in this package also it is static

import org.testng.Assert;


public class TuTor1 {
	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String res = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(AddPlacepackage.files()).when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		//for given body we can use different ways to calculate... refer payload package for more understanding or udemy section 5.19
		
//		server validation at header response is must to check whether data is fetch from proper server
		/*
		 * We are just adding the place and validating the assertions until now. 
		 * but what if we have a requirement to add a place then update the same place with new place and to fetch whether the new place is updated or not?
		 * we have to store the add place operation in one variable to use it in update and fetch.
		 */
//		USE ABOVE CODE FOR "ADD PLACE". 
//		FOR UPDATE PLACE WE NEED "PLACE ID", SO WE NEED TO RETRIVE THAT PLACE ID = STORE IT IN SOMEWHERE SO WE CAN REUSE IT IN OTHER PLACES WHEN NEEDED. 
		System.out.println(res); // we can remove the log().all() if needed now. I am keeping it for my memory
//		if we are using body in then(), then it is sufficient to validate using assertion but as we are extracting, we need to parse the place_id and get hold of that value, for this we can use jsonPath
//		JasonPath class is the one which takes string as an input and converts that into Json and it will help to parse the Json
		JsonPath js = new JsonPath(res);
//		for path we can use location.lat or location.lang
		String placeid = js.getString("place_id");
		System.out.println(placeid);
		
		
//		UPDATE API
		
		String newaddress = "Bay Area, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "    \"place_id\" : \""+placeid+"\",\r\n"
				+ "    \"address\" : \""+newaddress+"\",\r\n"
				+ "    \"key\" : \"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
//		GETAPI -> header is not needed as we are not passing any body 
		
	/*	given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid)
		.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).body("address", equalTo("Bay Area, USA"));
	
	* the above code is commented becoz it is in general way, look down to see the different way
	*/	
		String updatedaddress = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).when().get("maps/api/place/get/json").then().assertThat().statusCode(200).extract().response().asString();
		//JsonPath js1 = new JsonPath(updatedaddress);// created a seperate class and calling from there
		JsonPath js1 = rawToJson.rawJson(updatedaddress);
		String actual = js1.getString("address");
		System.out.println(actual);
		
//		as we came out of restassured lib, Java itself donot have any assertion methods to compare the values to check wther address is updated or not so we need to relay on any other testing frameworks to use assertion methods. there are 2 testing frameworks which are popular in JAVA, 
//		Cucumber BDD JUnit, (TestNG - we implemented by adding testng jar file in build path properties)
		Assert.assertEquals(actual, newaddress); //-> this is testNG framework jar file
		
		
	}

}
