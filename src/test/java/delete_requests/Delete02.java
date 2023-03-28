package delete_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDeleteBodyPojo;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyRestApiBaseUrl {
    /*
Given URL: https://dummy.restapiexample.com/api/v1/delete/2
When HTTP Request Method: DELETE Request
And Test Case: Type by using Gherkin Language
Then Assert:     i) Status code is 200
            ii) "status" is "success"
            iii) "data" is "2"
            iv) "message" is "Successfully! Record has been deleted"

  */
    @Test
    public void delete02(){
        spec.pathParams("first","delete","second",2);

        DummyRestApiDeleteBodyPojo expectedData = new DummyRestApiDeleteBodyPojo("success","2","Successfully! Record has been deleted");

        Response response = given().spec(spec).delete("/{first}/{second}");
        response.prettyPrint();

        DummyRestApiDeleteBodyPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiDeleteBodyPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getData(),actualData.getData());
        assertEquals(expectedData.getMessage(),actualData.getMessage());


    }
}
