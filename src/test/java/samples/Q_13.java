package samples;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static java.util.Collections.max;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Q_13 extends DummyRestApiBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language

           Assert:  i) Status code is 200
                    ii) There are 24 employees
                   (HamcrestMatcher kullanarak 24 employees olduğunu doğrulayınız)
                    iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   (HamcrestMatcher kullanarak "Tiger Nixon" ve "Garrett Winters"'ın employees arasında olduğunu doğrulayınız' )
                    iv) The greatest age is 66
                   (En büyük yaşın 66 olduğunu doğrulayınız)
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   (En düşük yaşın isminin Tatyana Fitzpatrick olduğunu doğrulayınız)
                    vi) Total salary of all employees is 6,644,770
                   (Tüm employees salary toplamının  6,644,770 olduğunu doğrulayınız)
    */
    @Test
    public void getDummy(){
        //Given user goes https://dummy.restapiexample.com/api/v1/employees
        //When  HTTP Request Method: GET Request
        //Then  Status code should be 200
        //And   There should be  24 employees
        //And   "Tiger Nixon" and "Garrett Winters" should be among the employees
        //And   The greatest age should be 66
        //And   The name of the lowest age should be "Tatyana Fitzpatrick"
        //And   Total salary of all employees should be 6,644,770

        spec.pathParam("first","employees");

        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        //And   There should be  24 employees
        //And   "Tiger Nixon" and "Garrett Winters" should be among the employees
        List<Object>dataList= response.jsonPath().getList("data");
        //System.out.println("dataList = " + dataList);
        response.then().statusCode(200)
                .body("data.id",hasSize(24),"data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

        //And   The greatest age should be 66
        List<Integer>ageList=response.jsonPath().getList("data.findAll{it.employee_age}.employee_age");
        System.out.println("ageList = " + ageList);
        int theGreatestAge =max(ageList);
        System.out.println("theGreatest = " + theGreatestAge);
        assertEquals(66,theGreatestAge);

        //And   The name of the lowest age should be "Tatyana Fitzpatrick"
        List<String> name=response.jsonPath().getList("data.findAll{it.employee_age==19}.employee_name");
        System.out.println("name = " + name);
        assertEquals(name.get(0),"Tatyana Fitzpatrick");  //[Tatyana Fitzpatrick] jsonPath getString dersem böyle alıyordu assert olmadı, getList şeklinde alıp
                                                                                                //listin ilk elemanını çekince assert oldu
        //And   Total salary of all employees should be 6,644,770

        List<Integer >allSalaries=response.jsonPath().getList("data.employee_salary");
        System.out.println("allSalaries = " + allSalaries);
        int totalSalary=0;
       for(Integer w: allSalaries){
           totalSalary+=w;

       }
       double result= (double)totalSalary;
        System.out.println(result);


    }
}
