package get_Request;


import base_urls.HerokuappBesaUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerokuappBesaUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Aaron" and last name is "Chen"
     */

    @Test
    public void test01() {
        //1. step: set the url
        //https://restful-booker.herokuapp.com/booking?firstname=Aaron&lastname=Chen olusacak
        spec.pathParam("first","booking").
                queryParams("firstname","GGS",
                        "lastname","FINCH");

        // 2. step set the expecdet data

        //3. Step: Send the Request and get the Response
      Response response= given().spec(spec).when().get("/{first}");
      response.prettyPrint();

      //4. step Do Assertions
        response.then().assertThat().statusCode(200);
        assertTrue(response.asString().contains("bookingid"));

    }
}
