package get_requests;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15 extends RestfulBaseUrl {
    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                   {
            "firstname": "Guoqiang",
            "lastname": "Morante Briones",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Breakfast"
}
     */
    @Test
    public void get15(){
        // 1=> Set the Url
        spec.pathParams("first", "booking","second",22);

        // 2=> Set the Expected Data
        BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData=new BookingPojo("Guoqiang","Morante Briones",111,
                true,bookingDatesPojo,"Breakfast");

        // 3=> Send the Request and Get the Response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4=> Do Assertion
        BookingPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(),BookingPojo.class);

        // HARD ASSERTION
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());

        // SOFT ASSERTION
        // 1.ADIM => SoftAssert Objesi olusturulur
        SoftAssert softAssert=new SoftAssert();

        // 2.ADIM => Assertion Yap
        softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname(),"Firstname eslesmedi");
        softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname(),"Lastname eslesmedi");
        softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice(),"Total Price eslesmedi");
        softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid(),"Deposit eslesmedi");
        softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds(),"Additional needs eslesmedi");

        softAssert.assertEquals(actualData.getBookingdates().getCheckin(),bookingDatesPojo.getCheckin(),"Checkin eslesmedi");
        softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds(),"Checkout eslesmedi");


        // 3.ADIM => assertAll() methodunu kullan
        softAssert.assertAll();
    }
}
