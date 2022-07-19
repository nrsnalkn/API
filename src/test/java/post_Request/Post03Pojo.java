package post_Request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonplaceholderPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonplaceholderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
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
        //1. Step Set the url
        spec.pathParam("first", "todos");

        //2.Step Set the expecdet Data
        JsonplaceholderPojo requastBody=new JsonplaceholderPojo(55,"Tidy your room",false);

        //3.Step Send Post Request and get the Respans
      Response response=  given().spec(spec).contentType(ContentType.JSON).body(requastBody).when().post("/{first}");
        response.prettyPrint();
        //4.Step Do Assertion
        //1. yol
       JsonplaceholderPojo acualBody= response.as(JsonplaceholderPojo.class); // response mi pojo formatina cevirdik
        System.out.println(acualBody);
        assertEquals(requastBody.getUserId(),acualBody.getUserId());
        assertEquals(requastBody.getTitle(),acualBody.getTitle());
        assertEquals(requastBody.getCompleted(),acualBody.getCompleted());
        //2. yol
        assertEquals(requastBody.toString(), acualBody.toString());




    }

}
