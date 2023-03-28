package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Put01 extends JsonPlaceHolderBaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/201
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									    "id": 198
									   }
     */
    @Test
    public void put01(){
        //set the url
        spec.pathParams("first","todos","second",198);

        //set the data
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",21.0);
        expectedData.put("title","Wash the dishes");
        expectedData.put( "completed",false);


        //send the request get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().body(expectedData).put("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object>actualData=response.as(HashMap.class);
        System.out.println(actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
    }


    @Test  //DYNAMIC WAY
    public void put02(){
        //set the url
        spec.pathParams("first","todos","second",198);

        //set the data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object>expectedData= obj.expectedDataMethod(21,"Wash the dishes",false);

        //send the request get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().body(expectedData).put("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object>actualData=response.as(HashMap.class);
        System.out.println(actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
    }
}
