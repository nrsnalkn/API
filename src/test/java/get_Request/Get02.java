package get_Request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get02 {
  /*
        Given
            https://restful-booker.herokuapp.com/booking/1 -- calisma sayfasi oldugu icin bazen ekleme olabilir
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy" (header) header methodu ile yapilir
     */

    @Test
    public void test01() {
        // 1.Step :Set the URL
        String url="https://restful-booker.herokuapp.com/booking/1";

        // 2.Step: Set the expected data(post, put, patch)

        // 3.Step: Type code to send reguest
        Response response =given().when().get(url);
        response.prettyPrint(); // body icinde olani verir bize

        // iv-Do Assertion -- dogrulama yapma
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
        // Response body'de bulunan spesifik bir veri nasil assert olup olmadigini edilir
        //asserTrue() methodunun icindeki deger true ise test gecer
        assertTrue(response.asString().contains("Not Found"));
        //asserFalse() methodunun icindeki deger false ise test gecer
        assertFalse(response.asString().contains("TechProEd"));


        System.out.println(response.header("Server"));// bana cowboy verir
        // assertEquals("") methodu parantez icindeki iki deger esit ise
        assertEquals("Cowboy",response.header("Server"));


    }
}
