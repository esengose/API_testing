package samples;

import base_urls.AutomationExcBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q_10 extends AutomationExcBaseUrl {
    /*2)
	Given
		https://automationexercise.com/api/productsList
	When
		User sends Get request
	Then
		Assert that number of "Women" usertype is 12
		(Kadın usertype sayısının 12 olduğunu doğrulayın)

*/
    @Test
    public void automationEx(){
        spec.pathParams("first","api","second","productsList");

        Response response = given(spec).when().accept(ContentType.JSON).get("/{first}/{second}");

        //response.jsonPath().prettyPrint(); //BURADA JSONPATH DEMESSEK HTML GELİYOR, ACCEPT METHODU YUKARIDA İŞE YARAMADI

        JsonPath jsonPath = response.jsonPath(); //GROOVY LANGUAGE İLE KARMAŞIK İÇ İÇE DATALARA ULAŞICAM, FİLTRELEME YAPICAM, JSONPATH OBJ OLUŞTURUYORUM
        int womenuser=jsonPath.getList("products.findAll{it.category.usertype.usertype=='Women'}.usertype").size();
        System.out.println("womenuser = " + womenuser);

        assertEquals(12,womenuser);


    }
}
