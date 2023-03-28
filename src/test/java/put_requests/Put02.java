package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {
    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */
    @Test
    public void put02(){
        //set the url
        spec.pathParams("first","update","second",21);

        //set the expected data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Ali Can",111111,23,"Perfect image");
        System.out.println("expectedData = " + expectedData);

        //tüm levelleri (scope) kapsayan bir pojo oluşturuyorum ki actual datayla assert edebileyim
        DummyRestApiResponseBodyPojo expectedBody=new DummyRestApiResponseBodyPojo("success",expectedData,"Successfully! Record has been updated.");

        //send the request get the response
        Response response = given().when().spec(spec).body(expectedData).put("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        DummyRestApiResponseBodyPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiResponseBodyPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedBody.getStatus(),actualData.getStatus());
        assertEquals(expectedBody.getMessage(),actualData.getMessage());

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(),actualData.getData().getProfile_image());

    }
}
