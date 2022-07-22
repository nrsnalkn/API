package post_Request;

import base_urls.DummyRestApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyBodyPojo;
import pojos.DummyDataPojo;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05 extends DummyRestApi {
    /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body: {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */
    /*
    Test Case: Type by using Gherkin Language
    Given
            URL: https://dummy.restapiexample.com/api/v1/create
     (Post yaparken iki kosul var birincisi givende body olmali)
            {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
     When
            User sends the Post Request
     Then
            Status code is 200
     And
        {
            "status": "success",
            "data": {
                 "employee_name": "Tom Hanks",
                 "employee_salary": 111111,
                  "employee_age": 23,
                  "profile_image": "Perfect image",
                  "id": 6344
                  },
             "message": "Successfully! Record has been added."
                    }



     */

    @Test
    public void test01() {

        //1.Step Set the url
        spec.pathParam("first","create");

        //2.Step Set the expecdet Data
        DummyDataPojo dummyData=new DummyDataPojo("Tom Hanks",111111,23,"Perfect image");
        DummyBodyPojo expectedData=new DummyBodyPojo("success",dummyData,"Successfully! Record has been added.");

        //3.Step Send the get Requast and get the Response
        Response response =given().spec(spec).contentType(ContentType.JSON).body(dummyData).when().post("/{first}");
        response.prettyPrint();

        //4.Step Do Assertion
        DummyBodyPojo actualData=JsonUtil.convertJsonToJavaObject(response.asString(), DummyBodyPojo.class);
        System.out.println(actualData);

        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(), actualData.getData().getProfile_image());

    }
}
