package get_requests;

import base_url.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get06b extends ReqresBaseUrl {
     /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */

    @Test
    public void get01(){

        // 1=> Set The Url
        spec.pathParam("first","unknown");

        // 2=> Set The Expected Data

        // 3=> Send The Request And Get Response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4=> Do Assertion
        JsonPath jsonPath=response.jsonPath();

        // 1)Status code is 200
        assertEquals(200,response.statusCode());

        // 2)Print all pantone_values
        System.out.println(jsonPath.getList("data.pantone_value"));

        // 3)Print all ids greater than 3 on the console
        // Assert that there are 3 ids greater than 3
        //System.out.println(jsonPath.getList("data.id")); //TUm Id'leri list seklinde yazdirir
        List<Integer> ids=jsonPath.getList("data.findAll{it.id>3}.id");
        //NOT: findAll datalarina ulasmak istedigimiz collection baslangicinda [] kullanilir.
        System.out.println("ids = " + ids); //[4, 5, 6]
        assertEquals(3,ids.size());

        // 4)Print all names whose ids are less than 3 on the console
        // Assert that the number of names whose ids are less than 3 is 2
        List<String> names=jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("Id'si 3'ten kucuk olan isimler = " + names); // [cerulean, fuchsia rose]



        // NOT=> Data'nin size'ina su sekildede de ulasip assert edebiliriz. (Collection oldugu icin)
        response.then().body("data",hasSize(6));


    }


    // 1=> Set The Url
    // 2=> Set The Expected Data
    // 3=> Send The Request And Get The Response
    // 4=> Do Assertion
}
