package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get03 {
    /*Given
    https://jsonplaceholder.typicode.com/todos/23
    When
    User send GET Request to the URL
            Then
    HTTP Status Code should be 200
    And
    Response format should be “application/json”
    And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
    And
		    “completed” is false
    And
		    “userId” is 2*/

    @Test
    public void get03(){

            //set the url
            String url = "https://jsonplaceholder.typicode.com/todos/23";

            //set the expected data

            //set the request get the response
            Response response = given().when().get(url);
            response.prettyPrint();

            //Do assertion
            //1.yol
            response.then()
                    .statusCode(200)
                    .contentType("application/json")
                    .body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))//“title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
                    .body("completed",equalTo(false)) //completed” is false
                    .body("userId",equalTo(2)); //userId should be 2

            //2.yol
            response.then()    //tek body içerisinde assertion yaparak soft assertion yapmış oluruz. soft assertion ile fail olan stepler ile ilgili bilgi sahibi olurum
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title",equalTo("xet itaque necessitatibus maxime molestiae qui quas velit"),
                "completed",equalTo(false),
                "userId",equalTo(21));

        //Tek body() methodu içerisinde çoklu assertion yaparak soft assertion oluşturabilirsiniz. Fail durumunda body() içerisinde Java çalışmayı durdurmaz.
        //Çoklu body() methodu ile assertion yapıldığında fail durumunda Java bir sonraki body() methodu öncesi çaılışmayı durdurur.



    }
    }

