import dictionary.Dictionary;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by khanh
 * Date 11/17/2022 - 5:13 PM
 * Description: ...
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String filename = "slang.txt";
        Dictionary dict = new Dictionary();
        try {
            dict.importFromFile(filename);
        } catch (IOException e) {
            System.out.println("[ERROR]: " + e);
        }

        HashMap<String, HashSet<String>> test = dict.generateRandom();
        Map.Entry<String, HashSet<String>> entry = test.entrySet().iterator().next();
        System.out.println(entry.getKey() + " " + entry.getValue());

//        HashMap<String, Boolean> findHo = dict.searchDefsBySlang("Ho");
//        findHo.forEach((k, v) -> System.out.println(k));
//        System.out.println();
//        findHo = dict.searchDefsBySlang("AB");
//        findHo.forEach((k, v) -> System.out.println(k));
//        System.out.println();
//        findHo = dict.searchDefsBySlang("KA");
//        findHo.forEach((k, v) -> System.out.println(k));
//        System.out.println();
//        findHo = dict.searchDefsBySlang("^");
//        findHo.forEach((k, v) -> System.out.println(k));
//        System.out.println();
//        findHo = dict.searchDefsBySlang(".");
//        findHo.forEach((k, v) -> System.out.println(k));
//        System.out.println();


//


    }
}