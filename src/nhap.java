/**
 * PACKAGE_NAME
 * Created by khanh
 * Date 11/17/2022 - 10:20 PM
 * Description: ...
 */
// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

class Nhap {
    private static HashMap<String, HashSet<String>> dictionary = new HashMap<>();

    public static void main(String[] args) {
        Nhap nhap = new Nhap();
        HashSet<String> test = new HashSet<>();
        test.add("1");
        test.add("2");
        test.add("3");
        dictionary.put("item1", test);
        // System.out.println(dictionary.get("item1"));
        test = new HashSet<>();
        test.add("4");
        test.add("5");
        test.add("6");
        dictionary.put("item2", test);
        System.out.println(nhap.searchSlangsByDef("1 2"));
    }

    public ArrayList<Map.Entry<String, HashSet<String>>> searchSlangsByDef(String def) {
        String[] arrOfStr = def.split(" ");
        return dictionary
                .entrySet()
                .stream()
                .filter(d -> {
                    for (String word : arrOfStr)
                        if (d.getValue().contains(word))
                            return true;
                    return false;
                }).collect(Collectors.toCollection(ArrayList::new));
    }
}
