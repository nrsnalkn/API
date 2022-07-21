package get_Request;

import base_urls.DummyRestApi;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get16 extends DummyRestApi {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employees
    When
        User send Get Request
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "Tatyana Fitzpatrick"
    And
        Total salary of all employees is 6,644,770


     */
    @Test
    public void test01() {
        //1. Step Set the url
        spec.pathParam("first","employees");

        //2.Step Set the expected Data

        //3.Step Send the get Requast and get the Response
       Response response=given().spec(spec).when().get("/{first}");
       response.prettyPrint();

        //4.Step Do Assertion


        //iii) "Tiger Nixon" and "Garrett Winters" are among the employees
        response.then().
                assertThat().contentType(ContentType.JSON).
                statusCode(200).// i) Status code is 200
                body("data.id",hasSize(24),// ii) There are 24 employees
                "data.employee_name", hasItems("Tiger Nixon","Garrett Winters"));//iii) "Tiger Nixon" and "Garrett Winters" are among the employees

        // iv) The greatest age is 66
        JsonPath json=response.jsonPath();
      List<Integer> ageList= json.getList("data.findAll{it.id}.employee_age");
        System.out.println(ageList);// sirali getirmedi
        Collections.sort(ageList); // sirali yaptik ve kalici hale geldi
        System.out.println(ageList);

        assertTrue(ageList.get(ageList.size() - 1)==66);//boylede olur
        assertEquals(66,(int)ageList.get(ageList.size() - 1));//explicitly

        //v) The name of the lowest age is "Tatyana Fitzpatrick"
      /* 1.yol
       List<Integer> nameAge19= json.getList("data.findAll{it.id==15}.employee_name");
        List<Integer> name1Age19= json.getList("data.findAll{it.employee_name==19}.employee_name");
        System.out.println(name1Age19);
        assertEquals("Tatyana Fitzpatrick",name1Age19.get(0));*/
        //2.yol
        String groovyString="data.findAll{it.employee_age=="+ ageList.get(0)+ "}.employee_name";
        String minAgeEmployeeName= json.getString(groovyString);
        System.out.println(minAgeEmployeeName);
        assertEquals("[Tatyana Fitzpatrick]",minAgeEmployeeName);

        //vi) Total salary of all employees is 6,644,770

        //1.yol

       List<Integer> salary = json.getList("data.findAll{it.id}.employee_salary");//tum salary getirir
        System.out.println(salary);
        int toplam=0;
        for (int each:salary) {
            toplam += each;

        }
        System.out.println("sum salary: " + toplam);
        assertEquals(6644770,toplam);

        //2.yol
       int sumLambda1= salary.stream().reduce(0,(t,u)->t+u);
        System.out.println("lamda"+ sumLambda1);
        assertEquals(6644770,sumLambda1);

        //3.yol
       int sumLambda= salary.stream().reduce(0,Math::addExact);
        System.out.println("lamda"+ sumLambda);
       assertEquals(6644770,sumLambda);








    }
}

