package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerOkuAppBaseUrl {
    /*
        Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
     */
    @Test
    public void post02(){
        //set the url
        spec.pathParam("first","booking");

        //set the expected data
        HerOkuAppTestData obj = new HerOkuAppTestData();
        Map<String,String> bookingdatesmap=obj.bookingdatesMapMethod("2021-09-09","2021-09-21");
        Map<String,Object>expectedData=obj.expectedDataMethod("John","Doe",11111,true,bookingdatesmap,null);

        //send the request get the response
        Response response = given().spec(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //do assertion  //burada json data içinde bir json daha var, onu map ile karşılaştırmak için typecasting yapıp map e dönüştürdük.
        // yaşanılan en sıkıntılı durumlardan biri de budur, type casting
        Map<String, Object>actualData=response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingdatesmap.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesmap.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));

    }
}
