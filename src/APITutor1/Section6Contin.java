//package APITutor1;
//
//import org.testng.annotations.Test;
//import org.testng.reporters.jq.Main;
//
//import io.restassured.path.json.JsonPath;
//import payLoad.AddPlacepackage;
//
//public class Section6Contin {
//
////	if we are running our test cases through Java, then everything will be wrapped in main() method
//	/*
//	 * as we have imported JUnit/TestNG then we don't have to implement main method.
//	 * instead we can use @Test annotation
//	 * 
//	 */
//
////		verify if sum of all course price matches with purchase amount
//	@Test
//	public void SumValidation() { // testNg framework will help to run this method without main method
//		JsonPath js = new JsonPath(AddPlacepackage.CoursePrice());
//		int count = js.getInt("courses.size()");
//		for (int i = 0; i < count; i++) {
//			int pric = js.getInt("courses[" + i + "].price");
//			int copi = js.getInt("courses[" + i + "].copies");
//			int amount = pric * copi;
//			System.out.println(amount);
//		}
//	}
//
//}
