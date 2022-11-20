package get_requests;

import base_url.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language

           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    /*  Gherkin Language
    Given => On kosullar
        https://dummy.restapiexample.com/api/v1/employees

    When
        User sends Get request

    Then => Assertions
         i) Status code is 200
    And
       ii) There are 24 employees
    And
      iii) "Tiger Nixon" and "Garrett Winters" are among the employees
    And
       iv) The greatest age is 66
    And
        v) The name of the lowest age is "Tatyana Fitzpatrick"
    And
       vi) Total salary of all employees is 6,644,770
     */

    @Test
    public void get16(){
        spec.pathParam("first","employees");

        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // NOT: Ayni body icinde birden fazla assertion yaptigimizda soft assertion olur. Hata aldigimizda diger
        // assertion'lar calismaya devam eder.
        response.then().assertThat().body("data.employees",hasSize(24),
                "data.employee_name",hasItems("Tiger Nixon", "Garrett Winters"));
        // body'deki key'lerden herhangi birinin sayisini hasSize() methoduyla aldik ve 24 tane olup olmadigini assert
        // yaptik. Sadece data ile de olurdu.
        // hasItems() ile employee_name'ler arasinda aranan isimlerin olup olmadigini assert yaptik.

        List<Integer> ages=response.jsonPath().getList("data.employee_age"); // Response'daki en buyuk yasi bulmak
        // icin yaslari json formatina cevirip getList() methoduyla list'e cevirip bir list'e atadik.
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        System.out.println("SortedAges = " + ages);
        // NOT=> List mutable'dir assign yapsak da yapmasak da sort yaptiktan sonra (uzerine yapilan degisiklikler)
        // uzerine kaydedilir. Memory'de yeni bir yer acmaz. String bunun tam tersidir.
        System.out.println(ages.get(ages.size()-1)); //66=> En sondaki elemani dondurdu
        assertEquals(66,(int)ages.get(ages.size()-1)); // Explicitly narrowing yaptik.
        // list'den gelen data'yi object olarak algiladi o yuzden hata verdi biz de basina(int) yazarak
        // => Explicitly narrowing yapmis olduk. Ve muhtemel hatayi önlemiş olduk..


    }

}
