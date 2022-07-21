package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {
    public Map<String, String> bookingdatesSetUp(String checkin, String checkout){
        Map<String, String> bookinDataMap=new HashMap<>();
        bookinDataMap.put("checkin",checkin);
        bookinDataMap.put("checkout",checkout);
        return  bookinDataMap;

    }
    public Map<String,Object> expecdetDataSetUp(String firstname, String lastname, Integer totalprice, Boolean depositpaid,Map<String, String> bookingdates){
      Map<String, Object> expectedDataMap=new HashMap<>();
      expectedDataMap.put("firstname",firstname);
      expectedDataMap.put("lastname",lastname);
      expectedDataMap.put("totalprice",totalprice);
      expectedDataMap.put("depositpaid",depositpaid);
      expectedDataMap.put("bookingdates",bookingdates);
      return  expectedDataMap;
    }
    public String herokuAppjsonToString(String firstname,String lastname,Integer totalprice,Boolean depositpaid,String checkin,String checkout,String additionalneeds) {

        String expectedData = "             {\n" +
                "\"firstname\": \"" + firstname + "\",\n" +
                "\"lastname\": \"" + lastname + "\",\n" +
                "\"totalprice\": " + totalprice + ",\n" +
                "\"depositpaid\": " + depositpaid + ",\n" +
                "\"bookingdates\": {\n" +
                "    \"checkin\": \"" + checkin + "\",\n" +
                "    \"checkout\": \"" + checkout + "\"\n" +
                "},\n" +
                "\"additionalneeds\": \"" + additionalneeds + "\"\n" +
                "}";
        return expectedData;
    }
}
