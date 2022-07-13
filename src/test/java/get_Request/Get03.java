package get_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import base_urls.JsonplaceholderBaseUrl;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get03 extends JsonplaceholderBaseUrl {


     /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
      And
          Response format should be "application/json"
      And
          "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
      And
          "completed" is false
      And
          "userId" is 2
     */


    @Test
    public void get01() {
        //1. Step: Set the Url
        // String url = "https://jsonplaceholder.typicode.com/todos/23"; // bu yazim sekli onerilmiyor
        spec.pathParams("first","todos","second",23);
        // firt utilities.BaseUrls den url (end point) getirdi url'den sonrasini sirayla yazdik

        //2. Step: Set the expected data


        //3. Step: Send the Request and get the Response
      Response response=  given().spec(spec).when().get("/{first}/{second}");

      response.prettyPrint();
        //4. Step: Do Assertion
        //1.yol
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).
                body("userId",equalTo(2));


            //2.yol
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),
                        "userId",equalTo(2));

    }
        /*
        Not1:-Assetion aparken Java calismayi durdurgunda hata sonrasi kodlar calismaz
        boylece hata sonrasi Assetion lar hakkinda bilgi sahibi olamayiz
        fakat hata oncesi Assetionlar gecmistir.
        Not2: Eger kodumuzu hata noktasinda duracak sekilde yazarsak "Hard Assetion" yapmis oluruz
        Not3: Eger kodumuzu hata noktasinda durmayacak sekilde yazarsak"soft Assertion" yapmis oluruz
        Not4 :Eger coklu body() methodu icerisinde assertion yaparsak "Hard Assertion" yapmis oluyoruz,
              tekli body() methodu icerisinde assertion yaparsak "soft Assertion" yapmis oluyoruz,

         */

    }
