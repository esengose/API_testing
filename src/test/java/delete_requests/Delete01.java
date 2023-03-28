package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Delete01 extends JsonPlaceHolderBaseUrl {
    /*
     Given
         https://jsonplaceholder.typicode.com/todos/198
     When
   I send DELETE Request to the Url
Then
   Status code is 200
   And Response body is { }
  */
    @Test
    public void delete01(){
        //set the url
        spec.pathParams("first","todos","second",198);

        //set the expected data
        Map<String,String> expectedData = new HashMap<>(); //boş map

        //send the request get the response
        Response response = given().spec(spec).delete("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map<String,String>actualData=ObjectMapperUtils.convertJsonToJava(response.asString(),HashMap.class); //boş map

        assertEquals(200,response.statusCode());
        assertEquals(expectedData,actualData); //1.yol

        assertTrue(actualData.isEmpty()); //2.yol

        assertEquals(0,actualData.size());



    }
}
