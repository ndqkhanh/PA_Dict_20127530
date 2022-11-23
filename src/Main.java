import gui.FrameUI;

import java.io.IOException;

/**
 * Created by khanh
 * Date 11/17/2022 - 5:13 PM
 * Description: ...
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FrameUI gui = new FrameUI();
        gui.createAndShowGUI();
//        String filename = "data.dat";
//        Dictionary dict = new Dictionary();
//        try {
//            File f = new File(filename);
//            if (!f.exists()) {
//                filename = "slang.txt";
//            }
//            dict.importFromFile(filename, false, false);
//        } catch (IOException e) {
//            System.out.println("[ERROR]: " + e);
//        }

//        HashMap<String, HashMap<String, Boolean>> findHo = dict.searchBySlang("Ho");
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