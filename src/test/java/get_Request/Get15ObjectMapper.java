package get_Request;

import base_urls.HerokuappBesaUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import test_data.HerOkuAppTestData;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper extends HerokuappBesaUrl {
    /*
    Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                                       {
                            "firstname": "Oliver",
                            "lastname": "Smith",
                            "totalprice": 100,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2022-07-18",
                                "checkout": "2022-07-19"
                            },
                            "additionalneeds": "Breakfast"
                        }
     */

    @Test
    public void test01() {
        //1.Step Set the url
        spec.pathParams("first", "booking","second",22);

        //2. Step Set the Expecdet Data
        String expectedData="{\n" +
                "                            \"firstname\": \"Oliver\",\n" +
                "                            \"lastname\": \"Smith\",\n" +
                "                            \"totalprice\": 100,\n" +
                "                            \"depositpaid\": true,\n" +
                "                            \"bookingdates\": {\n" +
                "                                \"checkin\": \"2022-07-18\",\n" +
                "                                \"checkout\": \"2022-07-19\"\n" +
                "                            },\n" +
                "                            \"additionalneeds\": \"Breakfast\"\n" +
                "                        }";
        HerOkuAppTestData herokuapp= new HerOkuAppTestData();

        BookingPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);


        //3.Step Send the Get Reguest get the Response
       Response response=given().spec(spec).when().get("/{first}/{second}");


       //4.Step Do Assertion
      BookingPojo actualDataPojo= JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataPojo.getFirstname(), actualDataPojo.getFirstname());
        assertEquals(expectedDataPojo.getLastname(), actualDataPojo.getLastname());
        assertEquals(expectedDataPojo.getTotalprice(), actualDataPojo.getTotalprice());
        assertEquals(expectedDataPojo.getDepositpaid(), actualDataPojo.getDepositpaid());
        assertEquals(expectedDataPojo.getAdditionalneeds(), actualDataPojo.getAdditionalneeds());
        assertEquals(expectedDataPojo.getBookingdates().getCheckin(), actualDataPojo.getBookingdates().getCheckin());
        assertEquals(expectedDataPojo.getBookingdates().getCheckout(), actualDataPojo.getBookingdates().getCheckout());


        // soft Assertion
        //1. adim Soft objesi olusturma
        SoftAssert softAssert=new SoftAssert();
        // 2. adim assertion yap
        softAssert.assertEquals(200,response.getStatusCode());
        softAssert.assertEquals(actualDataPojo.getFirstname(),expectedDataPojo.getFirstname(),"Firtname uyusmadi");
        softAssert.assertEquals(actualDataPojo.getLastname(),expectedDataPojo.getLastname(),"Lastname uyusmadi");
        softAssert.assertEquals(actualDataPojo.getTotalprice(),expectedDataPojo.getTotalprice(),"Totalprice uyusmadi");
        softAssert.assertEquals(actualDataPojo.getDepositpaid(),expectedDataPojo.getDepositpaid(),"Depositpaid uyusmadi");
        softAssert.assertEquals(actualDataPojo.getAdditionalneeds(),expectedDataPojo.getAdditionalneeds(),"Additionalneeds uyusmadi");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckin(),expectedDataPojo.getBookingdates().getCheckin(),"Checkin uyusmadi");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckout(),expectedDataPojo.getBookingdates().getCheckout(),"Checkout uyusmadi");



        //3. Adim assertAll();methodunu calistir
        softAssert.assertAll();


    }
}
