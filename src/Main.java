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
            if (!f.exists())
                filename = "slang.txt";
            File f2 = new File("indexSlang.dat");
            File f3 = new File("indexDef.dat");
            dict.importFromFile(filename, f2.exists() && f3.exists());
        } catch (IOException e) {
            System.out.println("[ERROR]: " + e);
        }
        FrameUI.createAndShowGUI(dict);
    }
}