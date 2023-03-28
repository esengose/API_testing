package samples;

import base_urls.PetStoreBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class Q_09 extends PetStoreBaseUrl {
    //1) https://petstore.swagger.io/ dokumanını kullanarak statüsü "available" olan "pet" sayısını bulup 100'den fazla olduğunu assert eden bir otomasyon testi yazınız.

 /*
    Given
        https://petstore.swagger.io/v2/pet/findByStatus?status=available
    When
        User sens Get request
    Then
        Assert that number of pets whose status is "available" is more than 100
     */
    @Test
    public void Q09test(){
        spec.pathParams("first","pet","second","findByStatus").queryParam("status","available");

       Response response = given().spec(spec).when().get("/{first}/{second}");
       //response.prettyPrint();

       int availablePetNum=response.jsonPath().getList("id").size();
        System.out.println("availablePetNum = " + availablePetNum);
        assertTrue(availablePetNum>100);


    }
}
