package APITutor1;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import payLoad.AddPlacepackage;

public class SumoValiTest {

  @Test
  public void sumOfCourTest() {

		int sum = 0;
		JsonPath js = new JsonPath(AddPlacepackage.CoursePrice());
		int count = js.getInt("courses.size()");
		for (int i = 0; i < count; i++) {
			int pric = js.getInt("courses[" + i + "].price");
			int copi = js.getInt("courses[" + i + "].copies");
			int amount = pric * copi;
			System.out.println(amount);
			sum = sum + amount;
			
		}
		System.out.println(sum);
		int purchase = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchase);
  }
}
