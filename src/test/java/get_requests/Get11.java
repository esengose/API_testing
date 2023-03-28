package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Kannan Ahluwalia", "The Hon. Tara Chaturvedi" and "Damayanti Dubashi" are among the users
        And
            The female users are less than or equals to male users
            (Kadın kullanıcı sayısı erkek kullanıcı sayısından küçük yada eşit olamlı)
     */
    @Test
    public void get11(){
        //set the url
        spec.pathParam("first","users");
        //set the expected data

        //send the request get the response
      Response response = given().spec(spec).get("{first}");
        response.prettyPrint();

        //do assertion
        response.
                then().
                statusCode(200).
                body("meta.pagination.limit",equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Gov. Vrinda Panicker", "Sen. Devika Embranthiri", "Rev. Jay Shukla" ) );

        //Kadın ve erkek sayılarını karşılaştıralım:

        //1.yol:
        JsonPath jsonPath = response.jsonPath(); //herseferinde response.jsonpath dememek için json objesi oluşturup heryerde onu kullanabiliriz
        List<String> genders=jsonPath.getList("data.gender");
        int kadinsayisi=0;
        for(String w: genders){
            if(w.equals("female")){
                kadinsayisi++;
            }
        }
        System.out.println("kadinsayisi = " + kadinsayisi);
        assertTrue(kadinsayisi<= genders.size()-kadinsayisi);

        //2.yol: kadın ve erkek sayılarını GROOVY kullanarak karşılaştıralım
        List<String>kadinKullaniciList = jsonPath.getList("data.findAll{it.gender=='female'}.gender"); //list "data" dan başlıyor, "data"yı belirtmek zorundayız
        System.out.println("kadinKullaniciList = " + kadinKullaniciList);
        List<String>erkekKullaniciList=jsonPath.getList("data.findAll{it.gender=='male'}.gender"); //groovy de String ' ' tek tırnakla belirtilir
        assertTrue(kadinKullaniciList.size()<=erkekKullaniciList.size());


    }
}
