package get_Request;

import base_urls.GoRestBaseUrl;

import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/298
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
         {

    "meta": null,
    "data": {
        "id": 298,
        "name": "Aarya Jain",
        "email": "jain_aarya@legros-lesch.info",
        "gender": "male",
        "status": "active"
    }
}
     */

    @Test
    public void test01() {
        // 1.step Set the url
        spec.pathParams("first", "users", "second", 298);

        // 2. step Set the expected data
        // verilen verileri map icine atali
        // ilk olarak icteki map yapalim
        Map<String, String> dataKeyMap = new HashMap<>();
        dataKeyMap.put("name", "Aarya Jain");
        dataKeyMap.put("email", "jain_aarya@legros-lesch.info");
        dataKeyMap.put("gender", "male");
        dataKeyMap.put("status", "active");

        // ikinci olarak dis map olusturalim
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("meta", null);
        expectedData.put("data", dataKeyMap);// icteki map distaki map ile birlestirdik
        System.out.println(expectedData);


        //3. step Send the reguest and the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        Map<String, Object> actualDataMap = response.as(HashMap.class);//json formatini java ya cevirdik
        System.out.println(actualDataMap);
        //4 . Step Do Assertion
        assertEquals(expectedData.get("meta"), actualDataMap.get("meta"));
        assertEquals(dataKeyMap.get("name"), ((Map) actualDataMap.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"), ((Map) actualDataMap.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map) actualDataMap.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map) actualDataMap.get("data")).get("status"));

    }

    @Test
    public void test02() {
        // 1.step Set the url
        spec.pathParams("first", "users", "second", 298);

        // 2. step Set the expected data
        GoRestTestData goRestTestData = new GoRestTestData();// obje olusturduk testdata classindaki methodlari kullanmak icin
        Map<String, String> dataKeyMap =
                goRestTestData.dataKeyMap("Aarya Jain", "jain_aarya@legros-lesch.info", "male", "active");
        // icteki map yaptik ve yeni bir map olusturup ona atadik

        Map<String, Object> expectedData = goRestTestData.expectedDataMap(null, dataKeyMap);
        // ust map cagirdik ve yeni map icine atadik


        //3.step Send the reguest and the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        // json objesinden java formatina cevirme De- Serializatio
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        //4. step Do Assertions

        assertEquals(expectedData.get("meta"), actualDataMap.get("meta"));
        assertEquals(dataKeyMap.get("name"), ((Map) actualDataMap.get("data")).get("name"));// once "Data" elemntine ulasip buradan aldigim objeyi map formatina yani cest ediyorum
        assertEquals(dataKeyMap.get("email"), ((Map) actualDataMap.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map) actualDataMap.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map) actualDataMap.get("data")).get("status"));


    }
}
