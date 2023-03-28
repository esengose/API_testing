package samples;

import base_urls.GmiBankBaseUrl;
import gmibank.pojos.Country;
import gmibank.pojos.States;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.AuthenticationGmiBank.getGmiBankToken;

public class Q_11 extends GmiBankBaseUrl {
    //https://app.swaggerhub.com/apis/yasinaniltechpro/GmiBank/0.0.1 dokümanını kullanarak en az 3 "state" içeren bir "country" oluşturan bir otomasyon testi yazınız.

    @Test
    public void postCountry(){
        //set the url ==> swagger dökümana göre ==> /api/tp-countries
        spec.pathParams("first","api","second","tp-countries");

        //set the expected data
        States state1 = new States(1,"New South Wales",null);
        States state2 = new States(2,"Queensland",null);
        States state3 = new States(3,"Western Australia",null);
        List<States>statesList = new ArrayList<>();
        statesList.add(state1);
        statesList.add(state2);
        statesList.add(state3);
        System.out.println("states = " + statesList);
        Country expectedData = new Country("Australia",statesList);
        System.out.println("expectedData = " + expectedData);

        //Send the request get the response
        Response response = given().spec(spec).header("Authorization",getGmiBankToken()).body(expectedData).post("/{first}/{second}");
        response.jsonPath().prettyPrint();

        //Do assertion
        Country actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),Country.class);

        response.then().statusCode(201);
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getStates().get(0),actualData.getStates().get(0));
        assertEquals(expectedData.getStates().get(1),actualData.getStates().get(1));
        assertEquals(expectedData.getStates().get(2),actualData.getStates().get(2));




    }
}
