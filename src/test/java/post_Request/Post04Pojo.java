package post_Request;

import base_urls.HerokuappBesaUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends HerokuappBesaUrl {
    /*
         Given
         1-   https://restful-booker.herokuapp.com/booking
         2-   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */
    @Test
    public  void test01(){
        //1.Step set the url
        spec.pathParam("first","booking");

        //2.Step Set the Expecdet Data
        BookingDatesPojo bookingdates=new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo requastBody= new BookingPojo("Ali", "Can", 999, true,bookingdates,"Breakfast with white tea, Dragon juice" );

        //3.Step send POST Request and  get the Response
       Response response=given().spec(spec).contentType(ContentType.JSON).body(requastBody).when().post("/{first}");
       response.prettyPrint();

       //4.step Do assertion
        response.then().assertThat().statusCode(200);
        BookingResponseBodyPojo actualBody=response.as(BookingResponseBodyPojo.class);

        assertEquals(requastBody.getFirstname(),actualBody.getBooking().getFirstname());
        assertEquals(requastBody.getLastname(),actualBody.getBooking().getLastname());
        assertEquals(requastBody.getTotalprice(),actualBody.getBooking().getTotalprice());
        assertEquals(requastBody.getDepositpaid(),actualBody.getBooking().getDepositpaid());
        assertEquals(requastBody.getBookingdates().getCheckin(),actualBody.getBooking().getBookingdates().getCheckin());
        assertEquals(requastBody.getBookingdates().getCheckout(),actualBody.getBooking().getBookingdates().getCheckout());
        assertEquals(requastBody.getAdditionalneeds(),actualBody.getBooking().getAdditionalneeds());



    }

}
