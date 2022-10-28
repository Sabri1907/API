package get_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get03 extends JsonPlaceHolderBaseUrl {

    /*
      Given
          https://jsonplaceholder.typicode.com/todos/23
      When
          User send GET Request to the URL
      Then
          HTTP Status Code should be 200
And
    Response format should be "application/json"
And
    "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
And
    "completed" is false
And
    "userId" is 2
   */

    @Test
    public void get01() {

        // Set the Url
        spec.pathParams("first","todos","second",23);
        // Extends yaptigimiz base_url.JsonPlaceHolderBaseUrl class'inda Url'imize ekleme yaptik.

        // Set the Expected Data (Put, Patch, Post)

        // Send the request and Get Response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertion

        // 1. YOL (Hard Assert)
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).
                body("userId",equalTo(2));

        // 2. YOL (Sadece Body icinde gecerli bir Soft Assert)
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON). // application/json karsiligi
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                "completed",equalTo("false"),"userId",equalTo(2));


    }
}