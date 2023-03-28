package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */
    @Test
    public void get08(){
        //set the url
        spec.pathParams("first","todos","second",2);

        //set the expected data
        JsonPlaceHolderTestData obj= new JsonPlaceHolderTestData();
        Map<String,Object>expectedData=obj.expectedDataMethod(1,"quis ut nam facilis et officia qui",false);
        expectedData.put("id",2);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        System.out.println("expectedData = " + expectedData);  //Get requestte exp.Data oluşturma sebebim Assertion u dynamic yapabilmek

        //send the request get the response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map<String,Object>actualData=response.as(HashMap.class); //De-serialization
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));

        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));


    }

}
