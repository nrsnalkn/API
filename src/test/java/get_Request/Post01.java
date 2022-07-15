package get_Request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonplaceholderBaseUrl {
      /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)
        When
            I send POST Request to the Url
        Then
            Status code is 201 {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void test01() {
        //1. step Set the url
        spec.pathParam("first","todos");


        //2. Step: Set the expected data
        //onceden olusturdumuz testData clasindan obje ile verileri ppost ile gondeiyoruzgonderiyoruz

        JsonPlaceHolderTestData jsonPlaceHolderTestData=new JsonPlaceHolderTestData();
       Map<String, Object> expecdetDataMap=
               jsonPlaceHolderTestData.expectedDataWhitAllKeys(55,"Tidy your room",false);

        //3. Step Send Post Reguast and the Response
    Response response=    given().spec(spec).contentType(ContentType.JSON).body(expecdetDataMap).when().post("/{first}");

        response.prettyPrint();

        //4. step Do Assertion
       Map<String, Object> actualDataMap= response.as(HashMap.class);
       assertEquals(expecdetDataMap.get("userId"),actualDataMap.get("userId"));
       assertEquals(expecdetDataMap.get("title"),actualDataMap.get("title"));
       assertEquals(expecdetDataMap.get("completed"),actualDataMap.get("completed"));
    }
}
