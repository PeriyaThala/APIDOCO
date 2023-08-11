package APITutor1;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter; // session filter class fetched from restassured java lib
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

public class JavaJira48 {

	public static void main(String[] args) {
		

		RestAssured.baseURI = "http://localhost:8080/";
//		Login Scenario
//		there is one way were we can parse the Json and use it in header for cookie similar to content type..// go through video 44 3:00 if needed.
//		another shortcut is explained here -> session filter

		SessionFilter session = new SessionFilter(); // created an object for rest assured filter session class.
//		"session" now has the ability to store the login credentials. it can be used before when() for all the operations performed
		
		/*
		 * In real time scenarios, there might be https instead of http. so restassured might not have that capability to bypass or authentication due to no proper certificates for such sessions
		 * due to this we can use another validation in given() called relaxedHTTPSValidation for successful authentication.
		 */

		String response = given().relaxedHTTPSValidation().header("Content-Type", "application/json").log().all()
				.body("{ \r\n" + "    \"username\": \"vasuvenky6499\", \r\n" + "    \"password\": \"1234\"     \r\n"
						+ "}")
				.filter(session).when().post("rest/auth/1/session").then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		String ExpectedMessage = "Hello Babeqqqqq!!!";

//		Add a comment to existing issue in Jira	

		String getcommentid = given().pathParam("id", "10008").log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \""+ExpectedMessage+"\",\r\n"
						+ "    \"visibility\": {\r\n" + "        \"type\": \"role\",\r\n"
						+ "        \"value\": \"Administrators\"\r\n" + "    }\r\n" + "}")
				.filter(session).when().post("rest/api/2/issue/{id}/comment") // if given in {} then it is referring to
																				// path parameter
				.then().extract().response().asString();
		JsonPath js1 = new JsonPath(getcommentid);
		String comentids = js1.getString("id");

//		Add attachment

		given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("id", "10008")
				.header("Content-Type", "multipart/form-data")
				.multiPart("file", new File("D:/APIDOCO/src/APITutor1/Jira.txt")) // MultiPart method is used to send
																					// any type of attachments in Java
																					// restAssured lib. also if that
																					// file is present in same project
																					// we can give just file name, else
																					// we have to give complete path. as
																					// file name can't be sent directly
																					// as value so we have to create a
																					// new class called File() and then
																					// give this link/file name. header
																					// should be given based on what
																					// type of body we are sending.
																					// earlier we have sent body json
																					// but here we are sending multipart
																					// attachment.
				.when().post("rest/api/2/issue/{id}/attachments").then().log().all().assertThat().statusCode(200);

//		Get details
		String comentget = given().filter(session).pathParam("id", "10008")// pathparam -> drills down to existing sub
																			// resource
				.queryParam("fields", "comment")// queryparam helps to filter/sort
				.when().get("rest/api/2/issue/{id}").then().extract().response()
				.asString();

		System.out.println(comentget);
		JsonPath js2 = new JsonPath(comentget);
		int commentssCount = js2.getInt("fields.comment.comments.size()");
		for(int u = 0; u < commentssCount; u++) {
			
			String idgen = js2.get("fields.comment.comments["+u+"].id").toString();			
			if(idgen.equalsIgnoreCase(comentids))
			{
				String ActualMessage = js2.get("fields.comment.comments["+u+"].body").toString();
				System.out.println(ActualMessage);
				Assert.assertEquals(ActualMessage, ExpectedMessage);
			}

		}

	}

}
