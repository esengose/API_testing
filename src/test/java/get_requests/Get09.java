package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
    /*
      Given
          https://restful-booker.herokuapp.com/booking/2170
      When
          I send GET Request to the url
      Then
          Response body should be like that;
           {
            "firstname": "John",
            "lastname": "Smith",
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
    public void get09(){
        //set the url
        spec.pathParams("first","booking","second","2170");

        //set the expected data
        Map<String, String> bookingdatesMap=new HashMap<>();  //INNER MAP, İÇ MAP, ÖNCE BUNU OLUŞTURDUK.
        bookingdatesMap.put("checkin","2018-01-01");
        bookingdatesMap.put("checkout","2019-01-01");

        Map<String,Object>expectedData=new HashMap<>();  //BÜYÜK MAP, INNER MAP İ DE KAPSIYOR
        expectedData.put("firstname","John");
        expectedData.put("lastname","Smith");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdatesMap);  //YUKARIDAKİ INNER MAP İ BURAYA "VALUE" OLARAK KOYDUK
        expectedData.put("additionalneeds","Breakfast");

        //send the request get the response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        System.out.println("expectedData = " + expectedData);



        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),(actualData.get("totalprice")));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

        assertEquals(bookingdatesMap.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));

        //actaldata dan çektiğim bookindates, (String, Obje) yapısından dolayı bana obje olarak dönüyor.
        // kendi içinde bir map olabilir ama obje data tipinde geliyor.bunun için (Map) diyerek Map e çeviriyorum ki, onun içinden de gerekli datayı alayım




    }

    @Test   //DYNAMIC WAY
    public void get09_2(){
        //set the url
        spec.pathParams("first","booking","second","794");

        //set the expected data
        HerOkuAppTestData obj = new HerOkuAppTestData();
        Map<String,String> bookingdatesMap =obj.bookingdatesMapMethod("2018-01-01","2019-01-01");
        Map<String,Object>expectedData=obj.expectedDataMethod("John","Smith",111,true,bookingdatesMap,"Dinner");

        //send the request get the response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        System.out.println("expectedData = " + expectedData);



        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),(actualData.get("totalprice")));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));




    }


}
