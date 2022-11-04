package get_requests;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get08 extends JsonPlaceHolderBaseUrl {

    //De-Serialization: Json datayı Java objesine çevirme
    //Serialization: Java objesini Json formatına çevirme.
    //De-Serialization: Iki türlü yapacağız.
    //Gson: Google tarafından üretilmiştir.
    //Object Mapper: Daha popüler...

      /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */


    @Test
    public void get08(){
        // 1=> Set The Url
        spec.pathParams("first","todos","second",2);

        // 2=> Set The Expected Data ==> PAYLOAD
        // ONEMLI NOT: Json dilindeki response'umuza yakin oldugu icin expected data'imizi map olusturduk boylece
        // serialization daha kolay yapilir. Ve boylece body() veya jsonPath() methodu olmadan response'u da map'e
        // cevirerek assert yapabiliriz.
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        System.out.println("expectedData = " + expectedData);
        // Value'ler karisik oldugu icin data tipini hepsini kapsadigi icin Object sectik.
        // Ama objenin dezavantaji ozellik olarak kisitlayicidir.



        // 3=> Send The Request And Get The Response
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4=> Do Assertion
        Map<String,Object> actualData=response.as(HashMap.class); // DeSerialization yaptik. response'la gelen json
        // dilindeki yapiyi map'e cevirdik.
        System.out.println("actualData = " + actualData);//{id=2, completed=false, title=quis ut nam facilis et officia qui, userId=1}
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        // Hangi data'nin hatali oldugunu bulmak icin map'i butun olarak degil baslikjlari ayri ayri assert yaptik.

        assertEquals("1.1 vegur",response.header("Via"));
        assertEquals("cloudflare",response.header("Server"));
        assertEquals(200,response.statusCode());


    }
    @Test
    public void get08b(){
        // 1=> Set The Url
        spec.pathParams("first","todos","second",2);

        // 2=> Set The Expected Data ==> PAYLOAD
        JsonPlaceHolderTestData objJsnPlcHldr=new JsonPlaceHolderTestData();
       Map<String, Object> expectedData=objJsnPlcHldr.expectedDataMethod(1,"quis ut nam facilis et officia qui",false);
        System.out.println("expectedData = " + expectedData);


        // 3=> Send The Request And Get The Response
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4=> Do Assertion
        Map<String,Object> actualData=response.as(HashMap.class); // DeSerialization yaptik. response'la gelen json
        // dilindeki yapiyi map'e cevirdik.
        System.out.println("actualData = " + actualData);//{id=2, completed=false, title=quis ut nam facilis et officia qui, userId=1}
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        //assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        // Hangi data'nin hatali oldugunu bulmak icin map'i butun olarak degil baslikjlari ayri ayri assert yaptik.

        assertEquals("1.1 vegur",response.header("Via"));
        assertEquals("cloudflare",response.header("Server"));
        assertEquals(200,response.statusCode());


    }
}
