import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03b extends ReqresBaseUrl {
    /*
   Given
       https://reqres.in/api/users/2
   When
       User send GET Request to the URL
   Then
       HTTP Status Code should be 200
   And
       Response format should be "application/json"
   And
       "email" is "janet.weaver@reqres.in",
   And
       "first_name" is "Janet"
   And
       "last_name" is "Weaver"
   And
       "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
*/

    @Test
    public void test03(){
        // 1=> Set the URL
        spec.pathParams("first", "users", "second",2);

        // 2=> Set The Expected Data

        // 3=> Send The Request and Get The Response
        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4=> Do Assertions
        response.then().
                statusCode(200).// API'da ilk statuscode kontrol edilir
                contentType(ContentType.JSON).
                body("data.email",equalTo("janet.weaver@reqres.in"),
                        "data.first_name",equalTo("Janet"),
                        "data.last_name",equalTo("Weaver"),
                        "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
                // Gerekli bilgileri kontrol etmek icin body icine girdik, ve body'deki ana yapinin icine
                // data.email yapisiyla ulastik.
    }
}
