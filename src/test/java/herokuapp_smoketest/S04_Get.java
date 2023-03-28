package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.S01_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class S04_Get extends HerOkuAppBaseUrl {
    @Test
    public void get01(){
        String exception="HttpResponseException: status code: 404, reason phrase: Not Found";
        try{
        spec.pathParams("first","booking","second",bookingId);

        String expectedData="Not Found";

        Response response = given().when().spec(spec).get("/{first}/{second}");

        response.prettyPrint();

        response.then().statusCode(404);
        assertEquals(expectedData,response.asString());
        } catch(Exception e){
            assertTrue(exception.contains("Not Found"));
            assertTrue(exception.contains("404"));
            System.out.println("Data can not be found");
        }

        }
}
