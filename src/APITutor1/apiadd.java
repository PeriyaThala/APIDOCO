package APITutor1;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class apiadd {

	public static void main(String[] args) {
//		Validating our add place api is working as expected.
//		Any rest assured automation should be written in these below 3 principles only.
//		Given - all i/p details-parameters,headers, body
//		When - methods + hitting/submitting the api(HTTPMethod + resource)
//		Then - validate the response - assertion,status code, log, all
//		before implementing the above 3, we have to set our base uri.
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"location\": {\r\n" + "        \"lat\": -38.383494,\r\n"
						+ "        \"lng\": 33.427362\r\n" + "},\r\n" + "    \"accuracy\": 50,\r\n"
						+ "    \"name\": \"Goldmine house\",\r\n" + "    \"phone_number\": \"(+91) 988 882 2222\",\r\n"
						+ "    \"address\": \"29, narrow layout, cohen 09\",\r\n" + "    \"types\": [\r\n"
						+ "    \"shoe park\",\r\n" + "    \"shop\"\r\n" + "    ],\r\n"
						+ "    \"website\": \"http://vasu.com\",\r\n" + "    \"language\": \"Tamil-IN\"\r\n" + "}")
				.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200);

	}

}
