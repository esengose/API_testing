package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;
import test_data.GoRestTestData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 247158,
                "name": "Chandak Dutta",
                "email": "dutta_chandak@bartoletti.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */
    @Test
    public void get13(){
        //set the url
        spec.pathParams("first","users","second",250);

        //set the expected data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo("Chandrabhan Joshi Jr.","joshi_jr_chandrabhan@blanda.name","male","active");
        GoRestPojo expectedData = new GoRestPojo(null,goRestDataPojo);
        System.out.println("expectedData = " + expectedData);

        //send the request get the response
        Response response = given().when().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(goRestDataPojo.getName(),actualData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actualData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(),actualData.getData().getStatus());
        assertEquals(expectedData.getMeta(),actualData.getMeta());

    }
}
