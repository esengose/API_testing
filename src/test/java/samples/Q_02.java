package samples;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Q_02 extends ReqresBaseUrl {
     /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty
     */

    @Test
    public void test02() {
        String exc;
        try {
            spec.pathParams("first", "api", "second", "users", "third", 23);

            Response response = given().spec(spec).when().get("/{first}/{second}/{third}");


            response.then()
                    .statusCode(404)
                    .statusLine("HTTP/1.1 404 Not Found");
            Assert.assertEquals("cloudfare",response.getHeader("Server"));
            Assert.assertEquals(2,response.asString().replaceAll("//s","").length());

       } catch (Exception e) {
            exc = "io.restassured.internal.http.HttpResponseException: status code: 404, reason phrase: Not Found";
            Assert.assertTrue(exc.contains("status code: 404"));
            Assert.assertTrue(exc.contains("Not Found"));
        }
    }
}
