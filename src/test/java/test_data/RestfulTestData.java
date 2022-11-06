package test_data;
import java.util.HashMap;
import java.util.Map;
public class RestfulTestData {
    public Map<String,String> bookingDatesMethod(String checkin,String checkout){
        Map<String,String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin",checkin); // ilk girilen key değişmiyor ve 2.si parametreden gelen ve benm sonradan gireceğim value
        bookingDatesMap.put("checkout",checkout);
        return bookingDatesMap;
    }
    public Map<String,Object> expectedDataMethod(String firstname,String lastname,Integer totalprice,Boolean depositpaid,Map<String,String> bookingdates){
        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname",firstname);
        expectedDataMap.put("lastname",lastname);
        expectedDataMap.put("totalprice",totalprice);
        expectedDataMap.put("depositpaid",depositpaid);
        expectedDataMap.put("bookingdates",bookingdates);
        return expectedDataMap;
    }
}