package other;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args) {

        TreeMap<String,String> treeMap = new TreeMap();
        treeMap.put("1", "a");
        treeMap.put("9", "b");
        treeMap.put("3", "c");
        treeMap.put("90", null);
        Set<Map.Entry<String, String>> entries = treeMap.entrySet();
        System.out.println(entries);
    }
}
