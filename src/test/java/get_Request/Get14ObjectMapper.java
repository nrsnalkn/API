package get_Request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonplaceholderPojo;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper extends JsonplaceholderBaseUrl {
    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void test01() {
        // 1. Step:  Set The Url
        spec.pathParams("first","todos","second",198);
        // 2. Step set the Expected Data
        String expectedData = "{\n" +
                "    \"userId\": 10,\n" +
                "    \"id\": 198,\n" +
                "    \"title\": \"quis eius est sint explicabo\",\n" +
                "    \"completed\": true\n" +
                "  }";
        HashMap<String,Object> expectedDataMap = JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);
        // 3. Step Send the GET Request and get the Response
        Response response= given().spec(spec).when().get("/{first}/{second}");
        // 4. Step:  Do Assertion
        assertEquals(200,response.getStatusCode());
        HashMap<String,Object> actualDataMap = JsonUtil.convertJsonToJavaObject(response.asString(),HashMap.class);
        assertEquals(expectedDataMap,actualDataMap);
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));



    }

    @Test
    public void test02() {// en iyi yontem
        // 1. Step:  Set The Url
        spec.pathParams("first","todos","second",198);
        // 2. Step set the Expected Data
        String expectedData = "{\n" +
                "    \"userId\": 10,\n" +
                "    \"id\": 198,\n" +
                "    \"title\": \"quis eius est sint explicabo\",\n" +
                "    \"completed\": true\n" +
                "  }";
        JsonplaceholderPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData, JsonplaceholderPojo.class);

        // 3. Step Send the GET Request and get the Response
        Response response= given().spec(spec).when().get("/{first}/{second}");

        //4.Step Do Assertion
        JsonplaceholderPojo actualDataPojo= JsonUtil.convertJsonToJavaObject(response.asString(),JsonplaceholderPojo.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataPojo.getUserId(),actualDataPojo.getUserId());
        assertEquals(expectedDataPojo.getTitle(),actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCompleted(),actualDataPojo.getCompleted());
    }
}

