package delete_Request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonplaceholderBaseUrl {
    /*
    Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */
    @Test
    public  void  test01(){
        //1.step Setvthe url
        spec.pathParams("first","todos","second",198);

        // 2. step Send the expected data

        Map<String, Object> expecdetData=new HashMap<>(); //bos bir map bu benim hazrldagim data

        //3. Step Send Delete Request and get the response

     Response response= given().spec(spec).when().delete("/{first}/{second}"); // bu rasi karsidan aldigim data// delete gonderildigi icin get degil delete yapiyoruz
        response.prettyPrint();

        //4. Step Do Assertion
        //1. yol
      Map<String, Object> actualDataMap= response.as(HashMap.class);// karsidan aldigim datalari map atadim
        response.then().assertThat().statusCode(200);
        assertEquals(actualDataMap, expecdetData); //  body'nin bos oldugu assert edildi\

        //2. yol
        assertTrue(actualDataMap.size()==0);
        assertTrue(actualDataMap.isEmpty());// tavsiye edilen bu bodynin bos oldugunu assert edilmesinde

        // Delete requeast yapmadan once "Post Request " yapip o datayi silebiliriz her hangi bir data kaybina sebep olmamak icin
        // kendi hazirladigin datayi sil

    }

}
