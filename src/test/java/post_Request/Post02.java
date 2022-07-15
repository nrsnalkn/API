package post_Request;

import base_urls.HerokuappBesaUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerokuappBesaUrl {
    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
     */

    @Test
    public void test01() {
        //1.Step Set the url
        spec.pathParam("first", "booking");

        //2.Step Set Ecpecdet the Data
        HerOkuAppTestData herOkuAppTestData = new HerOkuAppTestData();
        // ic teki map olusturuduk
        Map<String, String> bookingdatesMap = herOkuAppTestData.bookingdatesSetUp("2021-09-09", "2021-09-09");
        System.out.println(bookingdatesMap);
        //distaki map ve icteki mapi birlestirdik
        Map<String, Object> expectedDataMap = herOkuAppTestData.expecdetDataSetUp("Esat", "Alkan", 11111, true, bookingdatesMap);
        System.out.println(expectedDataMap);

        //3. Step Send  the Post Requast get the Respons
     Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");

    response.prettyPrint();
    assertEquals(200,response.statusCode() );

    // 4. Step Do Assertions
     Map<String,Object> actualDataMap=response.as(HashMap.class);

     assertEquals(expectedDataMap.get("firstname"),((Map)actualDataMap.get("booking")).get("firstname"));
     assertEquals(expectedDataMap.get("lastname"),((Map)actualDataMap.get("booking")).get("lastname"));
     assertEquals(expectedDataMap.get("totalprice"),((Map)actualDataMap.get("booking")).get("totalprice"));
     assertEquals(expectedDataMap.get("depositpaid"),((Map)actualDataMap.get("booking")).get("depositpaid"));


     assertEquals(bookingdatesMap.get("checkin"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
     assertEquals(bookingdatesMap.get("checkin"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));



    }
}
