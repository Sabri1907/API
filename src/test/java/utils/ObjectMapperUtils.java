package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class ObjeMapperUtils {
    // new ObjectMapper().readValue(jsonInString, HashMap .class);

    private static ObjectMapper mapper;
    static{
      mapper= new ObjectMapper(); //Objeck'in Herseyden (constructor'dan bile) once bir kez olusmasi icin static blok icinde
      // olusturduk.
    }

    public static <T> T convertJsonToJava(String json, Class<T> cls){ // Generic Method

        T javaResult=null;

        try {
            javaResult=mapper.readValue(json,cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
    return javaResult;
    }
}
