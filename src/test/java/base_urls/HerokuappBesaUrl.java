package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuappBesaUrl {
    protected RequestSpecification spec;
    //@Before annatation'i kullandigimiz da her test oncesi calisacak
    @Before
    public  void setUp(){
        spec= new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com").build();

    }
}
