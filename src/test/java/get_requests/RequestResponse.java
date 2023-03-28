package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.get;

public class RequestResponse {
    /*
    1) Postman manuel API testi için kullanılır.
    2) API otomasyonu için Rest-Assured Library kullanılır.
    3) Otomasyon kodlarının yazımı için şu adımları izliyoruz:
        a)Gereksinimleri anlama

        b)Test case i yazma:
            -Test case i yazmak için "Gherkin Language" kullaniyoruz.
             x) Given: Ön koşullar  --> Endpoint, body
             y) When : İşlemlee --> Get,Put,Delete...
             z) Then : Dönütler --> Assert
             t) And --> Çoklu işlemlerin art arda yazilacağı yerlerde kullanilir.

        c)Test kodunu yazarken şu adımları izleriz:
           i)    Set the URL
           ii)   Set the expected data
           iii)  Send the request and get the response
           iv)   Do assertion

// ilk yapılması gereken Status Codu öğrenmektir.
     */
    public static void main(String[] args) {
        // get request nasıl yapılır:
        Response response = get("https://restful-booker.herokuapp.com/booking/55");
        response.prettyPrint(); // bu method, response datayı yazdırır

        //status kod nasıl yazdırılır?
        System.out.println("response.statusCode() = " + response.statusCode());

        //content type nasıl yazdırılır?
        System.out.println("response.contentType() = " + response.contentType());

        //status line nasıl yazdırılır?
        System.out.println("response.statusLine() = " + response.statusLine());

        //header nasıl yazdıırlır?
        System.out.println("response.header(\"connection\") = " + response.header("connection"));
        System.out.println("response.header(\"server\") = " + response.header("server"));

        //headers nasıl yazdırılır?
        System.out.println("response.headers() = " + response.headers());

        System.out.println("response.getHeaders() = " + response.getHeaders());

        //time nasıl yazdıırlır?
        System.out.println("response.getTime() = " + response.getTime());

    }
}
