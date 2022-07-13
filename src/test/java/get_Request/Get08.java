package get_Request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonplaceholderBaseUrl {
    // De- Serialization:Json formatindan java objesine cevirme
    //Serialization: Java objesini  JSON formatina cevirmek icin
    //Gson:Goole tarafindan uretilmistir
    // object Mapper:Daha populer***

    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    @Test
    public void test01() {
        // 1. step set the url
        spec.pathParams("first","todos","second",2);

        // 2. step Set the expected data
        Map<String, Object> expectedData=new HashMap<>();
        expectedData.put("userId",1);
      //  expectedData.put("id",2);// yapilmamasi daha dogru sistem kendisi veriyor
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        expectedData.put("StatusCode",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");

        //3. step Send the reguest and the Response
     Response response=
             given().spec(spec).when().get("/{first}/{second}");

     // java formatina cevirme
       // response.as(HashMap.class); // hasmap'e cevirip map icine koyacaz

        Map<String,Object> actualData=response.as(HashMap.class);
        response.prettyPrint();

        // 4. step Do Assertion
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));

        assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));


    }

    @Test
    public void test02()  {
        // 1. step set the url
        spec.pathParams("first","todos","second",2);

        // 2. step Set the expected data
        JsonPlaceHolderTestData expectedData= new  JsonPlaceHolderTestData(); //data ekleme kismi methot olusturup obje ile cagirdik

     Map<String,Object> expectedDataMap=
             expectedData.expectedDataWhitAllKeys(1,"quis ut nam facilis et officia qui",false);

     expectedDataMap.put("StatusCode",200);
     expectedDataMap.put("Via","1.1 vegur");
     expectedDataMap.put("Server","cloudflare");
        //3. step Send the reguest and the Response
        Response response=
                given().spec(spec).when().get("/{first}/{second}");

        Map<String,Object> actualData=response.as(HashMap.class); // java formatina cevirdik
        System.out.println(actualData);

        //4. step Do Assertion

        assertEquals(expectedDataMap.get("userId"),actualData.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualData.get("completed"));
        assertEquals(expectedDataMap.get("userId"),actualData.get("userId"));

        assertEquals(expectedDataMap.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedDataMap.get("Via"),response.getHeader("Via"));
        assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));



    }
}
