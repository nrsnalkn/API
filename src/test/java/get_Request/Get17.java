package get_Request;

import base_urls.DummyRestApi;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyBodyPojo;
import pojos.DummyDataPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyRestApi {
    /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */
    /*
     Test Case: Type by using Gherkin Language
    Given
        https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."

     */

    @Test
    public void test01() {
        //1.Step Set the url
        spec.pathParams("first", "employee", "second", 1);

        //2.Step Set the Expected Data
        DummyDataPojo dataPojo = new DummyDataPojo("Tiger Nixon", 320800, 61, "");
        DummyBodyPojo dummyBodyPojo = new DummyBodyPojo("success", dataPojo, "Successfully! Record has been fetched.");
        //3.Step Send the get Requast and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.Step Do Assertion
        response.then().assertThat().statusCode(200);

        DummyBodyPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(), DummyBodyPojo.class);//java formatina cevirmek icin mapper kullandik

        assertEquals(dummyBodyPojo.getStatus(), actualDataPojo.getStatus());
        assertEquals(dummyBodyPojo.getStatus(), actualDataPojo.getStatus());
        assertEquals(dummyBodyPojo.getData().getEmployee_name(), actualDataPojo.getData().getEmployee_name());
        assertEquals(dummyBodyPojo.getData().getEmployee_age(), actualDataPojo.getData().getEmployee_age());
        assertEquals(dummyBodyPojo.getData().getEmployee_salary(), actualDataPojo.getData().getEmployee_salary());
        assertEquals(dummyBodyPojo.getData().getProfile_image(), actualDataPojo.getData().getProfile_image());
        assertEquals(dummyBodyPojo.getMessage(), actualDataPojo.getMessage());


    }
}
