package samples;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

public class Q_04 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
        When
            User sends get request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic" //DATA DEĞİŞEBİLİR

     */
    @Test
    public void test04(){
        spec.pathParam("first","booking").queryParams("firstname","John","lastname","Smith");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().statusCode(200);
        assertTrue(response.asString().contains("bookingid"));
    }
}
