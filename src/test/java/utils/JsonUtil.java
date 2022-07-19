package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

  private static ObjectMapper mapper;
  static {
      mapper = new ObjectMapper();
  }

  // 1. method: Json datasini java objesine cevirir (De- Serialization)

    public static <T> T convertJsonObject(String json, Class<T> cls){//Generic Method

      T javaResult=null;
        try {
           javaResult= mapper.readValue(json,cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return javaResult;
    }




    //2. method : Java objesini Json Datasina cevirir (Serializtion)

}
