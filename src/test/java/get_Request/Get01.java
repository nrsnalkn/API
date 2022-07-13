package get_Request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {
/*
    1-postman manuel api test icin kullanilir
    2-Api otomasyon testi icin rest-Assured Libary kullaniyoruz
    3-otomasyon kodlarinin yazimi icin su adimlari izliyoruz
        1-Gereksinimleri anlama
        2-Gest caselerin yazimi
            i- Test case yazimi icin Gherkin language kullaniyoruz
               1- Gherkin bazi keywordlare sahip bunlar
               a-Given: on kosullar
               b-When: aksiyonlar  Get, Put,....
               c-Then:Donutler dogrulama response...
               d-And:Coklu islemler icin kullanilir
        3-Testing kodunun yazimi
            i-Set the URL
            ii-Set the expected data(post, put, patch)
            iii-Type code to send reguest
            iv-Do Assertion
     */

    /*
    Given
            https://restful-booker.herokuapp.com/booking/55
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */
    @Test
    public void get01(){
        //  i-Set the URL
        String url="https://restful-booker.herokuapp.com/booking/55";

        //  ii-Set the expected data(post, put, patch)

        //  iii-Type code to send reguest
        Response response=  given().when().get(url);
        //response.prettyPrint(); // sonucu yazdiriyoruz

        //  iv-Do Assertion
        response.then().assertThat().statusCode(200).contentType("application/json")
                .statusLine("HTTP/1.1 200 OK");

        //status code nasil yazdirilir
        System.out.println("Status Code:"+ response.statusCode());
        //Content Type nasil yazdirilir
        System.out.println("Content Type: "+response.contentType());

        //status Line nasil yazdirilir
        System.out.println("Sttus Line: " + response.statusLine());

        //hedar nasil yazdirilir
        System.out.println(response.header("User-Agent"));

        //hedars nasil yazdirilir
        System.out.println("Headers\n" + response.headers());

        //Time nasil yazdirilir
        System.out.println("Time"+ response.getTime());


    }
}
