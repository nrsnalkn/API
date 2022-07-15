package get_Request;


import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Balachandra Sharma", "Atreyee Kakkar", "Kamala Trivedi DVM"are among the users
        And
            The female users are more than male users
     */

    @Test
    public void test01() {
        //1.step set the url
        spec.pathParam("first", "users");


        //2. step Send the Expected Data

        //3.step Send the reguest and the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4.step do assertion

        //1. yol body () methodu ile
        response.
                then().
                assertThat().
                body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id", hasSize(10), "data.status", hasItem("active"),
                        "data.name", hasItems("Aditeya Bhattacharya", "Gandharva Bhattacharya", "Sumitra Devar")); // id unuq oldugu icin id ile bulunur
        //Bayan ve erkek sayısını karşılaştıralım
        //1. Yol: Tüm cinisyetli çekip bayan sayısı ile karşılaştıralım.
        JsonPath json = response.jsonPath();
        List<String> genders = json.getList("data.gender"); // cinsiyetleri verir bunu bir liste atamamiz lazim
        System.out.println(genders);
        System.out.println(genders.size());
        int numOfFemales = 0;
        for (String w : genders) {
            if (w.equalsIgnoreCase("female")) {
                numOfFemales++;
            }

        }
        System.out.println(numOfFemales);//5 toplam cinsiyet cod yazildiginda 10 idi 5 erkek 5 kadin
        assertTrue(numOfFemales >= genders.size() / numOfFemales);

        //2. Yol:  Tüm bayan ve bayları ayrı ayrı Groovy ile çekelim.
        // bayanlari grovy ile cektik
      List<String> femaleList= json.getList("data.findAll{it.gender=='female'}.gender"); //Groovy ile yazdik ve liste atadik
        System.out.println("femaleList = " + femaleList);
        
        // erkekleri ggrovy ile cektik
      List<String> maleList=  json.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println("maleList = " + maleList);
        assertTrue(femaleList.size()==maleList.size());



    }
}
