package get_Request;

import base_urls.HerokuappBesaUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class Get06 extends HerokuappBesaUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/101
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
  {
    "firstname": "GGS",
    "lastname": "FINCH",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    }
}
     */

    @Test
    public void test01() {
        // 1.step set the url
        spec.pathParams("first","booking",
                "second",101);
        //2. step expected data

        // 3. step send the rugest end get the response
      Response response=  given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //4. step do assertions
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("GGS"),
                      "lastname",equalTo("FINCH"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"));

                    //2. yol JsonPath Class kullanilabilir
                        JsonPath json=response.jsonPath();
                       assertEquals("GGS" ,json.getString("firstname"));
                       assertEquals("FINCH" ,json.getString("lastname"));
                       assertEquals(111 ,json.getInt("totalprice"));
                       assertEquals(true ,json.getBoolean("depositpaid"));
                       assertEquals("2018-01-01" ,json.getString( "bookingdates.checkin"));
                       assertEquals("2019-01-01" ,json.getString( "bookingdates.checkout"));

      //3. yol
      // soft Assertions icin 3 adim izlenir
      // 1) softAssert objesi olusturulur
        SoftAssert softAssert=new SoftAssert();
      // 2) obje araciligi ile assert yapilir
        softAssert.assertEquals(json.getString("firstname"),"GGS","firstname uyusmadi");
        softAssert.assertEquals(json.getString("lastname"),"FINCH","lastname uyusmadi");
        softAssert.assertEquals(json.getInt("totalprice"),111,"totalprice uyusmadi");
        softAssert.assertEquals(json.getBoolean("depositpaid"),true);
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01");



        //3) assertAll() kullanilir kullanilmazsa kod her zaman pass olur
        softAssert.assertAll();





    }
}
