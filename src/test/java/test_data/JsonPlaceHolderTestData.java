package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
    public Map<String, Object> expectedDataWhitAllKeys(Integer userId, String title, Boolean completed){

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("userId",userId);
        expectedData.put("title",title);
        expectedData.put("completed",completed);

        return expectedData;

    }
    // patch olacagi zaman bu method kullanilir
    public Map<String, Object> expectedDataWithMissingAllKeys(Integer userId, String title, Boolean completed){
        Map<String,Object> expectedData=new HashMap<>();
        if(userId!=null){
            expectedData.put("userId",userId);
        }
        if(title!=null){
            expectedData.put("title",title);
        }
        if(completed!=null){
            expectedData.put("completed",completed);
        }
        return expectedData;
    }
    /*
    {
                "    \"userId\": 10,\n" +
                "    \"id\": 198,\n" +
                "    \"title\": \"quis eius est sint explicabo\",\n" +
                "    \"completed\": true\n" +
                "  }";*/

    public String expectedDataInString(Integer userId, String title , Boolean completed){
        String expectedData="";

        return expectedData;
    }
}
