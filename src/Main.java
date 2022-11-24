import dictionary.Dictionary;
import gui.FrameUI;

import java.io.File;
import java.io.IOException;

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
        FrameUI.createAndShowGUI(dict);

//        String[][] tmp = Dictionary.convertMapToList(dict.getDictionary());
//        for (String[] k : tmp) {
//            System.out.println(k[0] + "`" + k[1]);
//        }

//        HashMap<String, HashMap<String, Boolean>> findHo = dict.searchBySlang("Ho");
//        findHo = dict.searchBySlang("AB");
//        findHo = dict.searchByDef("BACK");
//        if (findHo != null)
//            findHo.forEach((key, value) -> value.forEach((k, v) -> {
//                System.out.println(key + ": " + k);
//            }));
//        System.out.println();
//        findHo = dict.searchBySlang("AB");
//        if (findHo != null)
//            findHo.forEach((key, value) -> value.forEach((k, v) -> {
//                System.out.println(key + ": " + k);
//            }));
//        System.out.println();
//        findHo = dict.searchByDef("BACK");
//        if (findHo != null)
//            findHo.forEach((key, value) -> value.forEach((k, v) -> {
//                System.out.println(key + ": " + k);
//            }));
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