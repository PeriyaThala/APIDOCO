package APITutor1;

import io.restassured.path.json.JsonPath;
import payLoad.AddPlacepackage;

public class ComplexJsonPath {

	public static void main(String[] args) {
		// until section 6

//		In agile, we might get the API contract first before the development completes, so until then we can't wait. we have to create a dummy(mock) api and start our automation based on that dummy api which can be build using contract. once dev completes, we can change the dummy api to developed one

		JsonPath js = new JsonPath(AddPlacepackage.CoursePrice());
//		Print No. of courses returned by API
//		Only for arrays we can use .Size() method
		int count = js.getInt("courses.size()");
		System.out.println(count);
//		Print PurchaseAmount
		int amount = js.getInt("dashboard.purchaseAmount");
		System.out.println(amount);
//		Print Title of the first course
		String booktitle = js.getString("courses[0].title"); // instead of getString we can use get only becoz by default
																// get will pull the string
		System.out.println(booktitle);
//      Print all course titles and their respective prices
		for (int i = 0; i < count; i++) {
			System.out.println(js.get("courses[" + i + "].title").toString()); // the output doesn't knows what return
																				// type it is(bcz we are using get, so
																				// we can either append with .tostring()
																				// method or we can assign it to a
																				// variable and print it.
			System.out.println(js.getInt("courses[" + i + "].price")); // we have used here getInt(), the o/p knows that
																		// the return type will be int.
		}

//		print no of copies sold by RPA. 
//		since arrays are dynamics we can't relay on array index. today 0th index might have title as RPA, tomorrow it might be any other index. 
		for (int i = 0; i < count; i++) {
			String course = js.getString("courses[" + i + "].title");
			if (course.equalsIgnoreCase("RPA")) // ignoreCase helps us in ignoring case sensitive: rpa = RPA = RpA
												// =rPA...
			{
				int copie = js.getInt("courses[" + i + "].copies");
				System.out.println(copie);
				break;
			}
		}
		
		
		

	}

}
