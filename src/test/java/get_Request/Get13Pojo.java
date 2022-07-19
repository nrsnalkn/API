package get_Request;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GorestDataPojo;
import pojos.GorestbodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            {

                    "meta": null,
                    "data": {
                        "id": 2508,
                        "name": "Akshita Nehru",
                        "email": "nehru_akshita@jast.info",
                        "gender": "female",
                        "status": "active"
                }
            }
     */

    @Test
    public void test01() {
        //1.Step Set the url
        spec.pathParams("first", "users","second",2508);

        //2.Step Set the expected data
        GorestDataPojo data= new GorestDataPojo(2508,"Akshita Nehru","nehru_akshita@jast.info","female","active");
        GorestbodyPojo gorestbody=new GorestbodyPojo(null,data);

        //3.Step Send Get Request and Get the Response
       Response response=given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();
    //4.step  Do Assert
        response.then().assertThat().statusCode(200);
      GorestbodyPojo acualBody=  response.as(GorestbodyPojo.class);
      assertEquals(gorestbody.getMeta(),acualBody.getMeta());

      assertEquals(gorestbody.getData().getId(),acualBody.getData().getId());
      assertEquals(gorestbody.getData().getName(),acualBody.getData().getName());
      assertEquals(gorestbody.getData().getEmail(),acualBody.getData().getEmail());
      assertEquals(gorestbody.getData().getGender(),acualBody.getData().getGender());
      assertEquals(gorestbody.getData().getStatus(),acualBody.getData().getStatus());


    }
}
