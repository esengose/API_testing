package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {
     /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends a GET request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Aaron" and last name is "Chen"
        (Data içerisinde firstname değeri "Sally", lastname değeri "Brown" olan biri olmalı)
 */
    @Test
    public void get05(){
        //set the url
        spec.pathParam("first","booking")
                .queryParams("firstname","Sally","lastname","Brown");  // typo issue yaşamamak için copy-paste yapılmalıdır!

        //set the expected data

        //send the request get the response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do assertion
        response.then().statusCode(200);

        assertTrue(response.asString().contains("bookingid")); //response stringe çevrilip burdan bir bookingid degeri dönüyorsa,
        //demek ki orada bir id ve deger vardır mantığıyla assertion yapılabilir

        //size da alınabilir, boş degilse bir bilgi dönmüştür
    }
}
