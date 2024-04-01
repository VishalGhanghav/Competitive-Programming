package Tests.src;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<Character,Integer> map=new HashMap<>();
        map.put('a',1);
        map.put('b',2);
        System.out.println("map:"+map);
    }
}
