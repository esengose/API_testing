package samples;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q_07 extends ReqresBaseUrl {
    /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */
    @Test
    public void test07(){
        //set the url
        spec.pathParam("first","users");

        //set the expected data
        ReqresTestData reqresTestData = new ReqresTestData();
        Map<String,Object>expectedData=reqresTestData.expectedDataMethod("morpheus","leader",null,null);
        System.out.println("expectedData = " + expectedData);

        //send the request get the response
        Response response = given().when().spec(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //do assertion
        Map<String,Object>actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));


    }
}
