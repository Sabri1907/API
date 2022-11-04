package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

    /*
    1-) Postman, manuel API testleri yapmak için kullanılır.
    2-) Otomasyon testleri için de Rest Assured Library kullanacağız.
    3-) Otomasyon testlerimizi yapmak için aşağıdaki adımları izliyoruz.
        a) Gereksinimleri anlamak
        b) Test Case yazılır
            i) Test Case yazımında "Gherkin" dilini kullanacağız.
               Bizler yazılım diline hakim olsak da karşımızdaki kişiler hakim olmayabilir.
               Ama Gherkin ile yazılan testleri anlamakta zorluk çekmeyecektir.
           = Gherkin dilinde kullanılacak keyword'lar:
               GIVEN : Ön Koşullar
               WHEN  : Yapılacak aksiyonlar (get(), put(), post(), patch() ve delete())
               THEN  : İstek yaptıktan (Request gönderdikten) sonra doğrulama
               AND   : Çoklu işlemlerde kullanacağız.

          c) Test kodlarımızı yazmaya başlayacağız.
              i) Set the URL
              ii) Set the Expected Data (Beklenen data'nın oluşturulması, post, put, patch)
              iii) Type code to send request (Talep göndermek için kod yazımı)
              iv) Do Assertion (Doğrulama yap)


     */

    /*
Given
        https://restful-booker.herokuapp.com/booking/101
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
 */

    @Test
    public void get01() {
        //i) Set the URL
        String url="https://restful-booker.herokuapp.com/booking/101";

        // ii) Set the Expected Data (Beklenen data'nın oluşturulması, post, put, patch)
        // Bizden post, put ya da patch istemediği için bu case'de kullanmayacağız.
        //iii) Type code to send request (Talep göndermek için kod yazımı)
        Response response = given().when().get(url); // Sorgumuzun cevabını almak için Response class'ını kullanırız.
        response.prettyPrint(); // Response'u yazdırmak için prettyPrint() kullanılır.

        // iv) Do Assertion (Doğrulama yap)
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");
        // Doğrulama genelde then()'de olur.
        // NOT: Content Type json yazılırsa hata verir. application/json şeklinde yazılmalıdır.

        // Status Code'u konsola yazdıralım.
        System.out.println("Status code: "+response.getStatusCode());

        // Content type'ı yazdıralım
        System.out.println("Content Type: "+response.getContentType());

        // Status Line'ı yazdıralım
        System.out.println("Status Line: "+response.getStatusLine());

        // Header konsola yazdıralım
        System.out.println("Header: "+response.getHeader("Server")); // String olarak belırtılen header'a ait değeri
        //getirir.

        //Headers yazdıralım
        System.out.println("Headers: "+response.getHeaders());

        //Time yazdıralım
        System.out.println("Time: "+response.getTime());

    }
}
