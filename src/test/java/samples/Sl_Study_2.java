package samples;

import base_urls.ZippopotamBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ZippopotamBodyPojo;
import pojos.ZippopotamPlacesPojo;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.assertEquals;

public class Sl_Study_2 extends ZippopotamBaseUrl {
/*
        Given
            http://api.zippopotam.us/ES/01001
       When
            Kullanıcı GET Methodu ile Request Gönder
        Then
             Status Code un "200" olduğunu Assert et
        And
            Response body nin bu şekilde olduğunu doğrula
            {
    "post code": "01001",
    "country": "Spain",
    "country abbreviation": "ES",
    "places": [
        {
            "place name": "Vitoria-Gasteiz",
            "longitude": "-2.6667",
            "state": "Pais Vasco",
            "state abbreviation": "PV",
            "latitude": "42.85"
        }
    ]
}
     */
    @Test
    public void zippoStudy(){
        spec.pathParams("first","ES","second","01001");

        ZippopotamPlacesPojo zippoPlaces = new ZippopotamPlacesPojo("Vitoria-Gasteiz","-2.6667","Pais Vasco","PV","42.85");
        ZippopotamBodyPojo expectedData = new ZippopotamBodyPojo("01001","Spain","ES",zippoPlaces);

        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getPostCode(),actualData.get("post code"));
        assertEquals(expectedData.getCountry(),actualData.get("country"));
        assertEquals(expectedData.getCountryAbbreviation(),actualData.get("country abbreviation"));
        assertEquals(zippoPlaces.getPlaceName(),(((Map)((List)actualData.get("places")).get(0))).get("place name"));
        assertEquals(zippoPlaces.getLongitude(),((Map)((List)actualData.get("places")).get(0)).get("longitude"));
        assertEquals(zippoPlaces.getState(),((Map)((List)actualData.get("places")).get(0)).get("state"));
        assertEquals(zippoPlaces.getStateAbbreviation(),((Map)((List)actualData.get("places")).get(0)).get("state abbreviation"));
        assertEquals(zippoPlaces.getLatitude(),((Map)((List)actualData.get("places")).get(0)).get("latitude"));




    }

}
