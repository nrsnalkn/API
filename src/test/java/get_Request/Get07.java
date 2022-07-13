package get_Request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonplaceholderBaseUrl {
    /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */

    @Test
    public void test01() {
        //1.step Set the url
        spec.pathParams("first","todos");

        //2. step Set the expecdet  data

        //3. Step Send the request and the respanse
       Response response= given().spec(spec).when().get("/{first}");
       response.prettyPrint();// yazdirma

       //4. Step Do Assertions
        response.then().assertThat().statusCode(200);
        //2)Print all ids greater than 190 on the console
        JsonPath json= response.jsonPath();
     List<Integer> ids= json.getList("findAll{it.id>190}.id"); // Groovy Languge java temelli bir programlama dili
     //(bu dosyadaki id 190'dan buyuk olanlari bana ver anlamina geliyor yukardaki yazim) ve bir liste atamasini yaptik

        System.out.println(ids);
        List<String> titles= json.getList("findAll{it.id==50}.title"); // id'si 50 olanin title
        System.out.println(titles);
        //Assert that there are 10 ids greater than 190
        assertEquals(10,ids.size());
        // 3)Print all userIds whose ids are less than 5 on the console
       List<Integer> userIds= json.getList("findAll{it.id<5}.userId");
        System.out.println(userIds);


        //Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(4,userIds.size());

        //4)Print all titles whose ids are less than 5
        List<Integer> userIdTitle= json.getList("findAll{it.id<5}.title");
        System.out.println(userIdTitle);
        //Assert that "delectus aut autem" is one of the titles whose id is
        assertTrue(userIdTitle.contains("delectus aut autem"));



    }
}
