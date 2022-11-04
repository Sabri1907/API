package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData {

    public Map<String, String> bookingdatesMethod(String checkin, String checkout){
        Map<String, String> bookingdatesMap=new HashMap<>();
        bookingdatesMap.put("checkin", checkin);
        bookingdatesMap.put("chechout", checkout);

        return bookingdatesMap;
    }

    public Map<String, Object> expecteddatamethod(String firstname, String lastname, Integer totalprice,
                                                  Boolean depositpaid, Map<String, String> bookingdates ){

        Map<String, Object> expecteddataMap = new HashMap<>();
        expecteddataMap.put("firstname", firstname);
        expecteddataMap.put("lastname", lastname);
        expecteddataMap.put("totalprice", totalprice);
        expecteddataMap.put("depositpaid", depositpaid);
        expecteddataMap.put("bookingdates", bookingdates);

        return expecteddataMap;
    }
}
