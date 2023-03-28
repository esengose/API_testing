package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {
    public static <T> T convertJsonToJava(String json, Class<T> cls) {//Generic Method ==>Herhangi bir data türüne dönecek demektir

        try {
            return new ObjectMapper().readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
