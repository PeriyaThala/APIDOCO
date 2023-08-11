package APITutor1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payLoad.AddPlacepackage;

import static io.restassured.RestAssured.*;

public class LibAPISec7 {

	@Test(dataProvider = "multiBook") // Made connection look below code to understand this. making connection alone
										// is not enough, as dataprovider is passing elements, we have to pass them in
										// below addbook() as arguments
	public void addbook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String responsee = given().header("Content-Type", "application/json")
				.body(AddPlacepackage.addbooks(isbn, aisle)) // for parameterization(adding multiple data at once) //("seop", "65657")-> to convert to
																// dynamic json, episode 34(adding single data but everytime new)
				.when().post("Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js2 = rawToJson.rawJson(responsee);
		String idd = js2.getString("ID");
		System.out.println(idd);

		/*
		 * To do parameterization to the test cases there is a annotation in TestNG
		 * called @DataProvider. set of data which you need for test need to be sent it as array.
		 */

	}

	@DataProvider(name = "multiBook") // use this data in Test NG for connection
	public Object[][] getData() {
		// Creating multidimentional array., set of data which you need for test need to be sent it
		// as array.
//		array - collection of elements. multidimentional array - collection of arrays
//		Basically to run the above program multiple times with different parameters(to add multiple books at one go)... we are creating an array to store those books details and adding them in @DataProvider and making connections between TestNG and DataProvider
		return new Object[][] { { "asjods", "123462" }, { "dsw0e", "32447" }, { "ass8ww", "324456" } };

	}
	
//	there are multiple ways to send static data... under given().body()-> you can send the request body directly or you can store it in separate class and call them(<classname>.<methodname>) but to not store the static data anywhere in the program and to call directly from outside any file from system then we can follow other method.
	/*
	 * body() will accept only string format.
	 * to convert the content of the given .json static file to string. 
	 * To do so there are methods in Java which converts content of the file into Bytes (given().body(Files.readAllBytes(Paths.get(<provide json file path>))))
	 * and there are methods in Java which converts bytes to string (given().body(new String(Files.readAllBytes(Paths.get(<provide json file path>))))). this is how we can read *static* data from external files 
	 */

}
