package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {
    // tektek datalari elle girmektende method olusturup cagirip
    // parametre olarak girmek daha az yer kaplar ve zaman tasarufu saglar
    public Map<String, String> dataKeyMap(String name, String email, String gender, String status){
      //icdeki map icin method olusturduk

        Map<String,String> dataKeyMap=new HashMap<>();
        dataKeyMap.put("name",name);
        dataKeyMap.put("email",email);
        dataKeyMap.put("gender",gender);
        dataKeyMap.put("status",status);

        return dataKeyMap;
    }
    public Map<String, Object> expectedDataMap(Object meta,Map<String, String> data){// ikinci value map oldugu icin parametre olarak map girdik
       //distaki map icin method olusturduk
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("meta",meta);
        expectedData.put("data",data);



        return  expectedData;
    }
}


