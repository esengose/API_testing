package samples;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Q_06 extends ReqresBaseUrl {
    /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
              (Tüm pantone_value değerlerini yazdırınız)
            3)Print all ids greater than 3 on the console
              (3'ten büyük id'leri yazdırınız)
              Assert that there are 3 ids greater than 3
              (3'ten büyük 3 adet id olduğunu doğrulayınız)
            4)Print all names whose ids are less than 3 on the console
              (id'si 3'ten küçük isimleri yazdırınız)
              Assert that the number of names whose ids are less than 3 is 2
              (id'si 3'ten küçük 2 isim olduğunu doğrulayınız)
    */
    @Test
    public void test06(){
        //set the url
        spec.pathParam("first","unknown");

        //set the expected data

        //send the request get the response
        Response response = given().when().spec(spec).get("{first}");
          //response.prettyPrint();

        //do assertion
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        // Print all pantone_values
        List<String> pantoneValueList=jsonPath.getList("data.pantone_value");
        System.out.println("pantoneValueList = " + pantoneValueList);

        //Print all ids greater than 3 on the console
        List<Integer>bigIds=jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println("bigIds = " + bigIds);

        //Assert that there are 3 ids greater than 3
        assertEquals(bigIds.size(),3);

        //Print all names whose ids are less than 3 on the console
        List<String>names=jsonPath.getList("data.findAll{it.id<3}.names");
        System.out.println("names = " + names);

        //Assert that the number of names whose ids are less than 3 is 2
        assertEquals(2,names.size());


    }


    }

