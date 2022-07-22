package put_Request;

import base_urls.DummyRestApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyBodyPojo;
import pojos.DummyDataPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyRestApi {
    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
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
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }


     */
    /*
    Test Case: Type by using Gherkin Language

    Given
       URL: https://dummy.restapiexample.com/api/v1/update/21
    And
                   {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
      When
            User send PUT Request to the Url
      Then
            Status code is 200
      And
            Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }



     */

    @Test
    public void test01() {
        //1.step Set the url
        spec.pathParams("first","update","second",21);
        //2.Step Set the expecdet Data
        DummyDataPojo dummyDataPojo=new DummyDataPojo("Esat Ali",111111,23,"Perfect image");
        DummyBodyPojo expectedData=new DummyBodyPojo("success",dummyDataPojo,"Successfully! Record has been updated.");

     Response response=   given().spec(spec).contentType(ContentType.JSON).body(dummyDataPojo).when().put("/{first}/{second}");
        response.prettyPrint();

        DummyBodyPojo actualData=JsonUtil.convertJsonToJavaObject(response.asString(),DummyBodyPojo.class);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getMessage(), actualData.getMessage());

        assertEquals(expectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(), actualData.getData().getProfile_image());
    }



    }

