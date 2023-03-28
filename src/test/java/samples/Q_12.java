package samples;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;

import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class Q_12 extends HerOkuAppBaseUrl {
     /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                  {
                    "firstname": "Jane",
                    "lastname": "Doe",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Extra pillows please"
                }
                (TestNG Soft assertion yapınız)
     */
    @Test
    public void getReq(){
        spec.pathParams("first","booking","second",22);

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Jane","Doe",111,true,"Extra pillows please",bookingDates);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),BookingPojo.class);
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(200,response.statusCode());
        soft.assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        soft.assertEquals(expectedData.getLastname(),actualData.getLastname());
        soft.assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        soft.assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        soft.assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
        soft.assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin());
        soft.assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());

        soft.assertAll();


    }

}
