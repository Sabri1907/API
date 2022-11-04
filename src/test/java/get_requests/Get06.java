package get_requests;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.*;

public class Get06 extends RestfulBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/2325
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}
     */

    @Test
    public void get01() {

        // 1. Set the Url (Url Olustur)
        spec.pathParams("first","booking","second",2325);

        // 2. Set the expected dataa (put, patch, post)

        // 3. Set The Resquest And Get The Response
        Response response=given().spec(spec).when().get("/{first{/{second}");
        response.prettyPrint();

        // 4. Do Assertion

        /*
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}
         */

        //  1. YOL

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Bradley"),
                        "lastname",equalTo("Pearson"),
                        "totalprice",equalTo(132),
                        "depositpaid",equalTo(false),
                        "bookingdates.checkin,",equalTo("2022-10-27"),
                        "bookingdates.checkout",equalTo("2022-11-07"),
                        "additionalneeds",equalTo("None"));

        // 2. YOL : Jsonpath class'inin kullanimi
        JsonPath json= response.jsonPath();

        assertEquals("Bradley",json.getString("firstname"));
        assertEquals("Pearson",json.getString("lastname"));
        assertEquals(132,json.getInt("totalprice"));
        assertFalse(json.getBoolean("depositpaid"));
        assertEquals("2022-10-27",json.getString("bookingdates.checkin"));
        assertEquals("2022-11-07",json.getString("bookingdates.checkout"));
        assertEquals("None",json.getString("additionalneeds"));

        // 3. YOL: Soft Assertion
        // Soft Assertion class 3 adimda kullanilir

        //i) Obje Olusturma
        SoftAssert softAssert = new SoftAssert();

        //ii) Do Assertion

        softAssert.assertEquals(json.getString("firstname"),"Bradleyx", "First Name Hatali");
        softAssert.assertEquals(json.getString("lastname"),"Pearson", "Last Name Hatali");
        softAssert.assertEquals(json.getString("totalprice"),133, "Total Price Hatali");
        softAssert.assertEquals(json.getBoolean("depositpaid"),false,"dsposited hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2022-10-27", "checkin Tarihi Hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2022-11-07", "checkout Tarihi Hatali");
        softAssert.assertEquals(json.getString("additionalneeds"),"None", "Additionalneeds Hatali");
        softAssert.assertAll();

        /* iii) Dogrulama islemleri sonunda softAssert.assertAll() diyerek yaptigimiz tum dogrulama islemlerinin kontrol
        edilmesini sagliyoruz.
        Eger islemin sonunda softAssert.assertAll() yapmazsak taleplerimiz hatali olsa testimiz pass olur.
         */


    }
}
