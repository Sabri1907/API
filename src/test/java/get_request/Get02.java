package get_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get02 {
    /*
    Given
    https://restful-booker.herokuapp.com/booking/1
    When
    User send a GET Request to the url
    Then
    HTTP Status code should be 404
    And
    Status Line should be HTTP/1.1 404 Not Found
    And
    Response body contains "Not Found"
    And
    Response body does not contain "TechProEd"
    And
    Server is "Cowboy"
    */

    @Test
    public void get01() {
        //i) Set the URL
        String url="https://restful-booker.herokuapp.com/booking/1";

        // ii) Set the Expected Data (Beklenen data'nın oluşturulması, post, put, patch)
        // Bizden post, put ya da patch istemediği için bu case'de kullanmayacağız.

        //iii) Type code to send request (Talep göndermek için kod yazımı)
        Response response=given().when().get(url);
        response.prettyPrint();

        //
        // iv) Do Assertion (Doğrulama yap)

        //  And Status Line should be HTTP/1.1 404 Not Found And HTTP Status code should be 404
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        // NOT= Contain'i yukarıdan farklı bir assert'le yapmamız gerekiyor.

        // And Response body contains "Not Found"
        assertTrue(response.asString().contains("Not Found"));
        // Body Not Fount içeriyor mu testi yapılıyor.
        // contains() methodunu kullanmak için response().asString().contains() yapısı kullanılır.
        // Sadece assertTrue() diyerek import edebiliriz.

        // And Response body does not contain "TechProEd"
        assertFalse(response.asString().contains("TechProEd"));
        // Body'nin TechProEd içermediği test ediliyor

        // And Server is "Cowboy"
        assertEquals("Cowboy",response.getHeader("Server"));
        // Server'in Cowboy olup olmadığı test ediliyor

    }
}
