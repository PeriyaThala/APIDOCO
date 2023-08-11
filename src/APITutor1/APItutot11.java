package APITutor1;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import payLoad.AddPlacepackage;

public class APItutot11 {

		@Test
		public void SumofCourse() {
		//Print No of courses returned by API
		
		JsonPath js = new JsonPath(AddPlacepackage.CoursePrice());
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//Print Purchase Amount
		
		int amount = js.getInt("dashboard.purchaseAmount");
		System.out.println(amount);
		
		//Print Title of the first course
		
		String title = js.get("courses[0].title");
		System.out.println(title);
		
		//Print All course titles and their respective Prices
		for(int i = 0; i<count; i++) {
			String coursetitle = js.getString("courses["+i+"].title");
			int courseprice = js.getInt("courses["+i+"].price");
			System.out.println(coursetitle + " " + courseprice);
		}
		
		//Print no of copies sold by RPA Course
		for (int i =0; i<count; i++) {
			String titlefind = js.getString("courses["+i+"].title");
			if(titlefind.equalsIgnoreCase("rpa")) {
				int copie = js.getInt("courses["+i+"].copies");
				System.out.println(copie);
			}
		}
		
		// Verify if Sum of all Course prices matches with Purchase Amount
		int total =0;
		for(int i = 0; i <count; i++) {
			
			int value = js.getInt("courses["+i+"].price");
			int cops = js.getInt("courses["+i+"].copies");
			int edgeprice = value *cops;			
			total = total + edgeprice;
		}
		System.out.println(total);
		Assert.assertEquals(total,amount);
	}

}
