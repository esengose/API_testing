package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static herokuapp_smoketest.S01_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

public class S02_Put extends HerOkuAppBaseUrl {
    /*
    1)Given https://restful-booker.herokuapp.com/booking/{id}
    2)2) {
            "firstname" : "Ali",
            "lastname" : "Can",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
    When
        Send Put request
    Then
        Status code should be 200
    And
          {
            "firstname": "Ali",
            "lastname": "Can",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Breakfast"
        }

     */


    @Test
    public void put01(){
        //set the url
        spec.pathParams("first","booking","second",bookingId); //post01deki booking id yi buraya getrdik

        //set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018.01.01","2019.01.01");
        BookingPojo expectedData = new BookingPojo("Ali","Can",111,true,"Breakfast",bookingDatesPojo);
        System.out.println("expectedData = " + expectedData);

        //send the request get the response
        Response response = given().spec(spec).body(expectedData).put("/{first}/{second}"); //baseurl içine token üreten methodu ekledik
        response.prettyPrint();

        //do assertion
        assertEquals(200,response.statusCode());

        BookingPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(),BookingPojo.class);

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());


        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());


    }
}
