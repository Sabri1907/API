package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Get10b extends GoRestBaseUrl {
     /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
   "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "inactive"
        }
*/

    @Test
    public void get10(){
        // 1=> Set The Url
        spec.pathParams("first","users","second",2986);

        // 2=> Set The Expected Data
        Map<String,Object> dataMap=new HashMap<>();
        dataMap.put("id",2986);
        dataMap.put("name","Navin Talwar");
        dataMap.put("email","navin_talwar@mclaughlin.name");
        dataMap.put("gender","male");
        dataMap.put("status","inactive");

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("meta",null);
        expectedData.put("data",dataMap);
        System.out.println("expectedData = " + expectedData);

        // 3=> Send The Request And Get The Response
        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4=> Do Assertion
        Map<String, Object> actualData=response.as(HashMap.class);

    }
}
