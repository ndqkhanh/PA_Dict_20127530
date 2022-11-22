import dictionary.Dictionary;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by khanh
 * Date 11/17/2022 - 5:13 PM
 * Description: ...
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String filename = "data.dat";
        Dictionary dict = new Dictionary();
        try {
            File f = new File(filename);
            if (!f.exists()) {
                filename = "slang.txt";
            }
            dict.importFromFile(filename, false, false);
        } catch (IOException e) {
            System.out.println("[ERROR]: " + e);
        }

//        HashMap<String, HashSet<String>> test = dict.generateRandom();
//        Map.Entry<String, HashSet<String>> entry = test.entrySet().iterator().next();
//        System.out.println(entry.getKey() + " " + entry.getValue());

        HashMap<String, HashMap<String, Boolean>> findHo = dict.searchDefsBySlang("Ho");
        if (findHo != null)
            findHo.forEach((key, value) -> value.forEach((k, v) -> {
                System.out.println(key + ": " + k);
            }));
        System.out.println();
        findHo = dict.searchDefsBySlang("AB");
        if (findHo != null)
            findHo.forEach((key, value) -> value.forEach((k, v) -> {
                System.out.println(key + ": " + k);
            }));
        System.out.println();
        findHo = dict.searchSlangsByDef("BACK");
        if (findHo != null)
            findHo.forEach((key, value) -> value.forEach((k, v) -> {
                System.out.println(key + ": " + k);
            }));
        System.out.println();
//        findHo = dict.searchDefsBySlang("^");
//        findHo.forEach((k, v) -> System.out.println(k));
//        System.out.println();
//        findHo = dict.searchDefsBySlang(".");
//        findHo.forEach((k, v) -> System.out.println(k));
//        System.out.println();


//


    }
}