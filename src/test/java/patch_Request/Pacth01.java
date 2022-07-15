package patch_Request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Pacth01 extends JsonplaceholderBaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url // kismi guncelleme
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */

    @Test
    public void test01() {
        //1.Step Set the url
        spec.pathParams("first", "todos", "second", 198);

        // 2. Step Set the Request Body
        JsonPlaceHolderTestData jsonpTestData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMap = jsonpTestData.expectedDataWithMissingAllKeys( null,"Wash the dishes",null );

    //3.Step Send the Patch Request get the response
      Response response=  given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().patch("/{first}/{second}");
   response.prettyPrint();

   //4.Step Do Assertion
     Map<String,Object>   mapToAssertAllDatals=jsonpTestData.expectedDataWhitAllKeys(10,"Wash the dishes",true);
        response.then().assertThat().statusCode(200).body("title",equalTo(mapToAssertAllDatals.get("title")));

    }
}
