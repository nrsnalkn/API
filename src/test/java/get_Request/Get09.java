package get_Request;

import base_urls.HerokuappBesaUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerokuappBesaUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/91
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
        "firstname": "James",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
         }
     */

    @Test
    public void test01() {
        // 1.step Set the url
        spec.pathParams("first", "booking", "second", 91);
        // 2. step Set the expected data
        // ic deki map yapalim
        Map<String, String> bookingDataMap = new HashMap<>();
        bookingDataMap.put("checkin", "2018-01-01");
        bookingDataMap.put("checkout", "2019-01-01");

        // disdaki mapi yapalim
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "James");
        expectedData.put("lastname", "Brown");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingDataMap); // distaki map'e icdeki map'i ekledik
        expectedData.put("additionalneeds", "Breakfast");
        System.out.println(expectedData);


        //3. step Send the reguest and the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        Map<String, Object> actualDataMap = response.as(HashMap.class); // De- Serialization:Json formatindan java objesine cevirme
        System.out.println(actualDataMap);


        //4 . Step Do Assertion
        assertEquals(expectedData.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualDataMap.get("depositpaid"));

        assertEquals(bookingDataMap.get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingDataMap.get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualDataMap.get("additionalneeds"));


    }
}
