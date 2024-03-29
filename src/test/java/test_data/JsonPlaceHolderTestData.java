package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
    public Map<String, Object> expectedDataWhitAllKeys(Integer userId, String title, Boolean completed) {

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);

        return expectedData;

    }

    // patch olacagi zaman bu method kullanilir
    public Map<String, Object> expectedDataWithMissingAllKeys(Integer userId, String title, Boolean completed) {
        Map<String, Object> expectedData = new HashMap<>();
        if (userId != null) {
            expectedData.put("userId", userId);
        }
        if (title != null) {
            expectedData.put("title", title);
        }
        if (completed != null) {
            expectedData.put("completed", completed);
        }
        return expectedData;
    }
    /*
                                       {
									    "userId": 10,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									    }
                */

    public String expectedDataInString(int userId,String title,boolean completed){
        String expectedData=   "{\n" +
                "   \"userId\": "+userId+"  ,\n" +
                "   \"title\": \""+title+"\",\n" +
                "   \"completed\": "+completed+"\n" +
                "  }";

        return expectedData;
    }
}
