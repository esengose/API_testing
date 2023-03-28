package samples;

import base_urls.Covid19BaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.junit.Test;
import pojos.CovidApiPojo;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Sl_Study_3 extends Covid19BaseUrl {
    /*
        Given
           https://api.covid19api.com/world/total
       When
            Kullanıcı GET Methodu ile Request Gönder
        Then
             Status Code un "200" olduğunu Assert et
        And
            Response body nin bu şekilde olduğunu doğrula
 {
    "TotalConfirmed": 674300771,
    "TotalDeaths": 6793224,
    "TotalRecovered": 0
}
     */

    @Test
    public void covidApiStudy(){
        spec.pathParams("first","world","second","total");

        CovidApiPojo expectedData = new CovidApiPojo(674300771,6793224,0);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        CovidApiPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),CovidApiPojo.class);
        System.out.println("actualData = " + actualData);  // !!! Tüm datalar null geldi !!!

        System.out.println();

       Map<String,Object> actualdataMap=response.as(HashMap.class);
        System.out.println("actualdataMap = " + actualdataMap);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getTotalConfirmed(),actualdataMap.get("TotalConfirmed"));
        assertEquals(expectedData.getTotalDeaths(),actualdataMap.get("TotalDeaths"));
        assertEquals(expectedData.getTotalRecovered(),actualdataMap.get("TotalRecovered"));
    }

}
