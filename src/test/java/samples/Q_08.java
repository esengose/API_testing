package samples;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.PetStorePojo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Q_08 extends PetStoreBaseUrl {
    /*
   "https://petstore.swagger.io/" dökümanını kullanarak 'user' oluşturacak bir otomasyon testi yazınız
    Not: Test Case'i gherkin language ile yazınız.
*/

          /*
        Given
            1) https://petstore.swagger.io/v2/user
            2) {
                  "username": "JohnDoe",
                  "firstName": "John",
                  "lastName": "Doe",
                  "email": "john@doe.com",
                  "password": "1234",
                  "phone": "1234",
                  "userStatus": 123
                }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "code": 200,
                                                "type": "unknown",
                                                "message": "6874988058"
                                             }
     */
    @Test
    public void test08() throws IOException {
        //set the url
        spec.pathParam("first","user");

        //set the expected data
        PetStorePojo expectedData = new PetStorePojo("JohnDoe","John","Doe","john@doe.com","1234","1234",123);
        System.out.println("expectedData = " + expectedData);

        //send the request get the response
        Response response = given().when().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}");
        response.prettyPrint();

        //do assertion
        Map<String,Object>actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(200,actualData.get("code"));
        assertEquals("unknown",actualData.get("type"));

    }

}
