package post_requests;
import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RestfulTestData;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
public class Post02 extends RestfulBaseUrl {
    /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/
    @Test
    public void post02() {
        // 1. Set The URL
        spec.pathParam("first","booking");
        // 2. Set The Expected Data ( put, post, patch)
        RestfulTestData obj = new RestfulTestData();
        Map<String,String> bookingDates = obj.bookingDatesMethod("2021-09-09","2021-09-21");
        Map<String,Object> expectedData = obj.expectedDataMethod("John","Doe",11111,true,bookingDates);
        System.out.println("expectedData = " + expectedData);
        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();
        // 4. Do Assertion
        Map<String,Object> actualdata = response.as(HashMap.class); // De-serialization
        System.out.println("actualdata = " + actualdata);
        assertEquals(expectedData.get("firstname"),((Map)(actualdata.get("booking"))).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)(actualdata.get("booking"))).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)(actualdata.get("booking"))).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map)(actualdata.get("booking"))).get("depositpaid"));
        assertEquals(bookingDates.get("checkin"),((Map)((Map)(actualdata.get("booking"))).get("bookingdates")).get("checkin"));
        assertEquals(bookingDates.get("checkout"),((Map)((Map)(actualdata.get("booking"))).get("bookingdates")).get("checkout"));
    }
}