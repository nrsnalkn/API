package get_Request;

import base_urls.HerokuappBesaUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class GetCalisma extends HerokuappBesaUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/555
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
            {
                "firstname": "Mary",
                "lastname": "Jackson",
                "totalprice": 111,
                "depositpaid": false,
                "bookingdates": { "checkin": "2017-05-23",
                                  "checkout":"2019-07-02" }
            }
     */

    @Test
    public void test01() {
        //1. step : set the url
        spec.pathParams("first","booking","second",555);
        // 2. step Set the expected data

        //3. step: Send ruquest end get the Response
       Response response= given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

       //4. step: Do Assertions
        //1. yol
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname",equalTo("\"Snezana\""),
                "lastname",equalTo("\"Petit\""),
        "totalprice",equalTo(215),"depositpaid",equalTo(true),
                "bookingdates", hasEntry("checkin","2022-07-07"),
                "bookingdates", hasEntry("checkout","2022-07-18"));
        //2. yol
        JsonPath json=response.jsonPath();
        assertEquals("\"Snezana\"",json.getString("firstname"));
        assertEquals("\"Petit\"",json.getString("lastname"));
        assertEquals(215,json.getInt("totalprice"));
        assertEquals(true,json.getBoolean("depositpaid"));
        assertEquals("2022-07-07",json.getString("bookingdates.checkin"));
        assertEquals("2022-07-18",json.getString("bookingdates.checkout"));


    }
}
