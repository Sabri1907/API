package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
    public Map<String,Object> expectedDataMethod(Integer userId, String title, Boolean completed){

        Map<String,Object> expectedDataMap=new HashMap<>();
        expectedDataMap.put("userId",userId);
        // expectedData.put("id",2); => id'yi zaten url parametresine girdigim icin burda belirtmeme gerek yok
        expectedDataMap.put("title",title);
        expectedDataMap.put("completed",completed);
        Boolean isNumber=null; // Boolean Wrapper class null kabul ettigi icin ve ileride null atamamiz gerektiginden
        // Boolean yaptik
        return expectedDataMap;
    }

}
