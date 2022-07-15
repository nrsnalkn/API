package put_Request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonplaceholderBaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */

    @Test
    public void test01() {
        //1.Step set the url
        spec.pathParams("first","todos","second",198);

        // 2.step Set Expecdet The Data
        JsonPlaceHolderTestData jsonTestData=new JsonPlaceHolderTestData();
       Map<String,Object> expecdetDataMap= jsonTestData.expectedDataWhitAllKeys(21,"Wash the dishes",false);
        System.out.println(expecdetDataMap);

        //3.Step Sendc the Put Requast get the Response
     Response response=
             given().spec(spec).
                     contentType(ContentType.JSON).body(expecdetDataMap).when().put("/{first}/{second}");

        response.prettyPrint();
        response.then().assertThat().statusCode(200);

        //4.Step Do Assertion
      Map<String,Object> actualDataMap=response.as(HashMap.class);

      assertEquals(expecdetDataMap.get("userId"),actualDataMap.get("userId"));
      assertEquals(expecdetDataMap.get("title"),actualDataMap.get("title"));
      assertEquals(expecdetDataMap.get("completed"),actualDataMap.get("completed"));


    }
}
