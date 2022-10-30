package get_request;

import base_url.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Get05b extends ReqresBaseUrl {

    /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json; charset=utf-8”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    public void get05(){
        // 1=> Set The Url
        spec.pathParams("first","unknown", "second",3);

        // 2=> Set The Expected Data

        // 3=> Send The Request And Get The Response
        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4=> Do Assertion
        SoftAssert softAssert=new SoftAssert(); // Soft Assert yapmak icin soft assert objesi olusturduk.

        // ONEMLI NOT=> Body icindeki bilgilere body() methoduyla ulasabiliriz ve kontrol (assert) edebiliriz ancak
        // disari bu bilgileri manipule edemeyiz. Manipule etmek icin jsonPath() methodu kullaniriz.
        JsonPath jsonpath=response.jsonPath(); // response.jsonPath() methodu ile response'umuzu jsobpath'e atamis olduk
        // ve istedigimiz gibi manipule edebiliriz.
        System.out.println(jsonpath.getInt("data.id")); // 3=> getInt methoduyla istedigimiz degeri aldik.
        System.out.println(jsonpath.getInt("data.id")+4); // 7=> Istedigimiz gibi manipule edebildik

        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        // Parametrelerden sonra fail oldugunda yazdirmak istedigimiz mesaji virgul koyarak "" icine yazariz
        softAssert.assertEquals(jsonpath.getInt("data.id"),3,"Id degeri dogru degil");
        softAssert.assertEquals(jsonpath.getString("data.name"),"true red","Name degeri dogru degil");
        softAssert.assertEquals(jsonpath.getInt("data.year"),2002,"Year degeri dogru degil");
        softAssert.assertEquals(jsonpath.getString("data.color"),"#BF1932","Color degeri dogru degil");
        softAssert.assertEquals(jsonpath.getString("data.pantone_value"),"19-1664","Pantone_Value degeri dogru degil");
        softAssert.assertEquals(jsonpath.getString("support.url"),"https://reqres.in/#support-heading","Url degeri dogru degil");
        softAssert.assertEquals(jsonpath.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!","Text degeri dogru degil");

        softAssert.assertAll();



    }
}
