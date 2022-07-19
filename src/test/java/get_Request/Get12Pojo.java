package get_Request;

import base_urls.HerokuappBesaUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends HerokuappBesaUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/18
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                "firstname": "omto",
                                "lastname": "nena",
                                "totalprice": 112,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2018-01-01",
                                    "checkout": "2019-01-01"
                                        },
                                 "additionalneeds": "Breakfast"
                                            }

     */
    @Test
    public  void test01(){
        //1.Step Set the url
        spec.pathParams("first","booking","second",18);

        //2.Step Set the Expected Data
        BookingDatesPojo bookingdates= new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo booking=new BookingPojo("omto","nena",112,true,bookingdates,"Breakfast");


        //3.Step Send Get Request and Get the Response
         Response response= given().spec(spec).when().get("/{first}/{second}");
         response.prettyPrint();

         // 4.Step Do Assertion
        response.then().assertThat().statusCode(200);
        BookingPojo acualBody= response.as(BookingPojo.class); //bookingBodyPojo yu kullanmama nedeni bize id verilmis
        assertEquals(booking.getFirstname(),acualBody.getFirstname());
        assertEquals(booking.getLastname(),acualBody.getLastname());
        assertEquals(booking.getTotalprice(),acualBody.getTotalprice());
        assertEquals(booking.getDepositpaid(),acualBody.getDepositpaid());
        assertEquals(booking.getAdditionalneeds(),acualBody.getAdditionalneeds());

        //1. yol
        assertEquals(booking.getBookingdates().getCheckin(),acualBody.getBookingdates().getCheckin());
        assertEquals(booking.getBookingdates().getCheckout(),acualBody.getBookingdates().getCheckout());

        //2. yol kisa oldugu icin bu daha kullanisli
        assertEquals(bookingdates.getCheckin(),acualBody.getBookingdates().getCheckin());
        assertEquals(bookingdates.getCheckout(),acualBody.getBookingdates().getCheckout());

    }
}
