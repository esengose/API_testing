package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {
    /*
         Given
           1)  https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
                }
        When
         I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post01(){
        //set the url
        spec.pathParam("first","todos");

        //set the expected data ==> ==> PAYLAOD
        Map<String, Object>expectedData = new HashMap<>();
        expectedData.put("userId",55.0);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);

        System.out.println("expectedData = " + expectedData); // {completed=false, title=Tidy your room, userId=55}

        //send the request get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().body(expectedData).post("/{first}");
        response.prettyPrint();

        //ben datayı oluşturmak için Java ile çalışıyorum, ama formatı API ya Java olarak gönderemem, bunu Json dependency,
        // olmazsa Gson dependency ile çevirmem lazım.Gson, serialization ya da deserialization için kullanılır.

        //post request yaparken contenttype Json OLARAK belirtirim, olmazsa, gson indirebilirm.



        //   De serialization (json objelerini java formatına cevirmektir)
        //   Serialization (java objelerini json formatina cevirmektir)
        //         i) Gson: Google tarafından üretilmiştir.
        //        ii) Object Mapper: En popüleri

        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);//response.as diyerek DE-Serialization ==> Json -> Java çevirme işlemi yaparız

        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));


    }
}
