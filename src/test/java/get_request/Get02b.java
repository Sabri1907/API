package get_request;

import base_url.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get02b extends ReqresBaseUrl {

     /*
   Given
       https://reqres.in/api/users/23
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Server is "cloudflare"
   And
       Response body should be empty

*/

    @Test
    public void get02(){
        // 1=> Set the URL
        spec.pathParams("first","users","second",23);

        // 2=> Set the expected data


        // 3=> Send the Request and Get the Response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4=> Do Assertion
        // System.out.println(response.statusCode()); // 404
        // HTTP Status code should be 404
        assertEquals(404,response.statusCode());

        // Status Line should be HTTP/1.1 404 Not Found
        assertEquals("HTTP/1.1 404 Not Found",response.statusLine());

        //Server is "cloudflare"
        assertEquals("cloudflare",response.getHeader("server")); // server body'de olmadigi icin
        // server value'sunu getHeader() methoduyla getirdik ve dogruladik

        // Response body should be empty
        assertEquals(2,response.asString().replaceAll("\\s","").length());

        // Bir body icinde sayi veya harf yoksa bos demektir. Burdan yola cikarak bosluklari hiclikle yer degistirdik
        // ve body icinde kalan karakter sayisinin 2 oldugunu test ettik.

    }

}
