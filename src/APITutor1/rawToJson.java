package APITutor1;

import io.restassured.path.json.JsonPath;

public class rawToJson {
	
	public static JsonPath rawJson(String response) {
		
		JsonPath js1 = new JsonPath(response);
		return js1;
		
	}

}
