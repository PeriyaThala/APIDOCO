package APITutor1;

import static io.restassured.RestAssured.given;


import io.restassured.path.json.JsonPath;

public class oAuth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Using restAssured we can play with API's but to get code from browser, please use selenium
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:/Program Files/Google/Chrome/Application"); WebDriver driver = new
		 * ChromeDriver(); driver.get(
		 * "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php"
		 * ); driver.findElement(By.cssSelector("input[type='email']")).sendKeys(
		 * "vasuv0503");
		 * driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.Enter
		 * ); 
		 * Thread.sleep(3000);
		 * driver.findElement(By.cssSelector("input[type='password']")).sendKeys(
		 * password);
		 * driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.
		 * Enter); 
		 * Thread.sleep(3000); 
		 * String url =driver.getCurrentUrl();
		 */
		
//		hey babies one major update -> Google sign in using any automation frameworks doesn't works as google not allows that post 2020. so we have to do manually.
		
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AVHEtk5-R_KRnPXVyRe_ED66JOhXRl-tgzdjRIR9GQQiOQxqxTZgX0vJI6oxeoaC4C_m-A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none"; //so directly pass the url. also we are using manually to get this code... we can request the developers to increase the time period for this url. so one link can stay active for 3 days etc.,
		
//		How parsing can be done??
		/*
		 * There is a method in Jave called "Split". This will be returning as array
		 */
		String parsingurlcode = url.split("code=")[1]; //0th index before code, 1st index after code
		String fulparsingcode = parsingurlcode.split("&scope=")[0];
		System.out.println(fulparsingcode);
		
		/*
		 * ONE IMPORTANT CONCEPT- RESTASSURED WILL NOT TAKE SPECIAL CHARATERS LIKE %., ETC., IT WILL CONVERT THEM TO NUMERICAL VALUES
		 * SO WE HAVE TO EXPLICITLY TELL RESTASSURED THAT DONT PERFORM ANY ENCODING.wHENEVER THERE IS SPECIAL CHARS THEN RESTASSURE WILL PERFORM 
		 * ENCODING. WE CAN PASS THE METHOD CALLED urlencodingenabled as "false"
		 * 
		 */
		
		
		String accessTokenResponse = given().urlEncodingEnabled(false).queryParam("Code", fulparsingcode).queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code").when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(accessTokenResponse);
		String extractedAccessToken = js.getString("access_token");
		
		String actualResponse = given().queryParam("access_token", extractedAccessToken).when().get("https://rahulshettyacademy.com/getCourse.php").asString(); // directly extracting to string. its just a shortcut. if needed we can use normal like in then() method instead of when() method.
		System.out.println(actualResponse);

	}

}
