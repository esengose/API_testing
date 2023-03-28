package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/2325
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
     {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}*/
    @Test
    public void get06() {
        //set the data
        spec.pathParams("first", "booking", "second", 23);

        //set the expected data

        //send the request get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1.yol
        response.then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("John"), "lastname", equalTo("Smith"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"), //burada data içinde data olduğu için, bookindate.checkin
                        "bookingdates.checkout", equalTo("2019-01-01"),    //diyerek inner Json a ulaşıyorum. Json in json
                        "additionalneeds", equalTo("Breakfast")
                );
        //2.yol
        JsonPath jsonPath = response.jsonPath();

        //Json datayla, String ve ya integer olabilir,boolean olabilir, işlem yapmak için JsonPath kullanmamız gerekir. Degerler json içine hapsolmuş gibi
        //düşünebiliriz. java kullandığımız heryerde jsonpath kullanabiliriz. dataları erişilebilir, işlem yapılabilir hale getirebiliriz.

        assertEquals("John", jsonPath.getString("firstname"));
        assertEquals("Smith", jsonPath.getString("lastname"));
        assertEquals(111, jsonPath.getInt("totalprice"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
        assertEquals("super bowls", jsonPath.getString("additionalneeds"));

        //3.yol
        //TestNG SOFT ASSERT
        //1.Soft assert objesi olustur

        //2. Assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(jsonPath.getString("firstname"), "Josh", "firstname uyuşmadı");
        softAssert.assertEquals(jsonPath.getString("lastname"), "Allen", "lastname uyuşmadı");
        softAssert.assertEquals(jsonPath.getInt("totalprice"), 111, "totalprice uyuşmadı");
        softAssert.assertEquals(jsonPath.getBoolean("depositpaid"), true, "depositpaid uyuşmadı");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01", "checkin uyuşmadı");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01", "checkout uyuşmadı");


        //3. softAssert.assertAll() diyerek doğrulamayı kontrol et. Aksi taktirde test hep "PASS" olur.
        softAssert.assertAll();

    }
}
