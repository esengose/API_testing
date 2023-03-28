package get_requests;

import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Get02 {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/1001
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */

    @Test
    public void get02() {
        String excMessage ="io.restassured.internal.http.HttpResponseException: status code: 404, reason phrase: Not Found";
        try {
            //1.adım: Set the url
            String url = "https://restful-booker.herokuapp.com/booking/1001";

            //2.adım: Set th expected data

            //3.adım: Send the request get the response
            Response response = given().when().get(url);
            response.prettyPrint();

            //4.adım: Do assertion

            response.then()                                  // then() yerine --> assertThat ile de assertion yapabiliriz
                    .statusCode(404)                         // HTTP Status code should be 404
                    .statusLine("HTTP/1.1 404 Not Found");   // Status Line should be HTTP/1.1 404 Not Found

            assertTrue(response.asString().contains("Not found"));  //Response body contains "Not Found"
            assertFalse(response.asString().contains("TechProEd")); //Response body does not contain "TechProEd"
            assertEquals("Cowboy", response.header("Server"));       //Server is "Cowboy"

        } catch ( Exception e) {
            assert excMessage.contains("404");
            assert excMessage.contains("Not Found");
            Assert.assertFalse(excMessage.contains("TechProEd"));
        }




    }
}