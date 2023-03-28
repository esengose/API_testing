package samples;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q_05 extends ReqresBaseUrl {
 /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json; charset=utf-8”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    public void test05() {
        //set the url
        spec.pathParams("first", "unknown", "second", 3);

        //set the expected data
      /*  Map<String, Object> datamap = new HashMap<>();
        datamap.put("id", 3);
        datamap.put("name", "true red");
        datamap.put("year", 2002);
        datamap.put("color", "#BF1932");
        datamap.put("pantone_value", "19-1664");
        Map<String, Object> supportmap = new HashMap<>();
        supportmap.put("url", "https://reqres.in/#support-heading");
        supportmap.put("text", "To keep ReqRes free, contributions towards server costs are appreciated!");
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("data", datamap);
        expectedData.put("support", supportmap);*/

        //send the request, get the response
        Response response = given().when().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        SoftAssert softassert = new SoftAssert();
        JsonPath jsonpath = response.jsonPath();

        softassert.assertEquals(200, response.statusCode());
        softassert.assertEquals("application/json; charset=utf-8", response.contentType());
        softassert.assertEquals(jsonpath.getInt("data.id"), 3, "id is not correct");
        softassert.assertEquals(jsonpath.getString("data.name"), "true red", "name is not correct");
        softassert.assertEquals(jsonpath.getInt("data.year"), 2002, "year is not correct");
        softassert.assertEquals(jsonpath.getString("data.color"), "#BF1932", "color is not correct");
        softassert.assertEquals(jsonpath.getString("data.pantone_value"), "19-1664", "pantone_value is not correct");
        softassert.assertAll();
    }




}
