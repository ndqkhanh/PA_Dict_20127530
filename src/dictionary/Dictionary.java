package dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * dictionary
 * Created by khanh
 * Date 11/17/2022 - 7:13 PM
 * Description: ...
 */
public class Dictionary {
    private final HistoryMap historyMap;
    private final HashMap<String, HashMap<String, Boolean>> indexSlang;
    private final HashMap<String, HashMap<String, Boolean>> indexDef;
    private HashMap<String, HashMap<String, Boolean>> dictionary;

    /**
     * Default constructor
     */
    public Dictionary() {
        historyMap = new HistoryMap();
        indexSlang = new HashMap<>();
        indexDef = new HashMap<>();
        dictionary = new HashMap<>();
    }

    public static String[][] convertMapToList(HashMap<String, HashMap<String, Boolean>> map) {
        ArrayList<String[]> mainList = new ArrayList<>();
        map.forEach((key, value) -> {
            value.forEach((k, v) -> {
                String[] tmp = {key, k};
                mainList.add(tmp);
            });
        });
        String[][] resList = new String[mainList.size()][];
        final int[] i = {0};
        mainList.forEach(line -> {
            resList[i[0]] = line;
            i[0] = i[0] + 1;
        });
        return resList;
    }

    public HistoryMap getHistoryMap() {
        return historyMap;
    }

    public void addIndexSlang(String slang) {
        for (int i = 1; i <= slang.length(); i++) {
            String part = slang.substring(0, i).toLowerCase();
            HashMap<String, Boolean> tmpMap = indexSlang.get(part);
            if (tmpMap != null) {
                tmpMap.putIfAbsent(slang, true);
            } else {
                tmpMap = new HashMap<>();
                tmpMap.put(slang, true);
                indexSlang.put(part, tmpMap);
            }
        }
    }

    public void addIndexDef(String def) {
        String[] parts = def.toLowerCase().split(" ");
        for (String part : parts) {
            HashMap<String, Boolean> tmpMap = indexDef.get(part);
            if (tmpMap != null) {
                tmpMap.putIfAbsent(def, true);
            } else {
                tmpMap = new HashMap<>();
                tmpMap.put(def, true);
                indexDef.put(part, tmpMap);
            }
        }
    }

    public void importIndexData(String filename, boolean isSlangIndex) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        while (true) {
            str = br.readLine();
            if (str == null)
                break;
            String[] parts = str.split(" ", 2);
            String[] values = parts[1].split("`");
            HashMap<String, Boolean> tmpMap = new HashMap<>();
            for (String v : values)
                tmpMap.put(v, true);
            if (isSlangIndex) indexSlang.put(parts[0], tmpMap);
            else indexDef.put(parts[0], tmpMap);
        }
        br.close();
    }

    public void exportIndexData(String filename, boolean isSlangIndex) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        (isSlangIndex ? indexSlang : indexDef).forEach((key, value) -> {
            try {
                StringBuilder message = new StringBuilder();
                value.forEach((k, v) -> {
                    message.append(k).append("`");
                });
                bw.write(key + " " + message.substring(0, message.length() - 1) + "\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        bw.flush();
        bw.close();
    }

    /**
     * @param filename
     * @param importIndexFile
     * @param importHistoryFile
     * @throws IOException
     */
    public void importFromFile(String filename, boolean importIndexFile, boolean importHistoryFile) throws IOException {
        dictionary = new HashMap<>();
        if (importIndexFile) {
            importIndexData("indexSlang.dat", true);
            importIndexData("indexDef.dat", false);
        }
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        while (true) {
            str = br.readLine();
            if (str == null)
                break;
            String[] slangAndDef = str.split("`");
            String[] defArray = slangAndDef[1].split("(\\| )");
            if (!importIndexFile) addIndexSlang(slangAndDef[0]);
            for (String def : defArray) {
                if (!importIndexFile) addIndexDef(def);
                addSlangWord(slangAndDef[0], def, false, false);
            }
        }
        if (filename.equals("slang.txt")) {
            exportToDataFile("data.dat");
            historyMap.exportToFile("history.dat");
        }
        if (!importIndexFile) {
            exportIndexData("indexSlang.dat", true);
            exportIndexData("indexDef.dat", false);
        }
        if (importHistoryFile)
            historyMap.importFromFile("history.dat");
    }

    public void exportToDataFile(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        dictionary
                .forEach((key, value) -> {
                    try {
                        StringBuilder message = new StringBuilder();
                        value.forEach((k, v) -> {
                            message.append(k).append("| ");
                        });
                        bw.write(key + "`" + message.substring(0, message.length() - 2) + "\n");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                });
        bw.flush();
        bw.close();
    }

    /**
     * @return
     */
    public HashMap<String, HashMap<String, Boolean>> generateRandom() {
        HashMap<String, HashMap<String, Boolean>> res = new HashMap<>();
        Object[] crunchifyKeys = dictionary.keySet().toArray();
        Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
        res.put(key.toString(), dictionary.get(key.toString()));
        return res;
    }

    /**
     * @return A dictionary list with slang words and their definitions
     */
    public HashMap<String, HashMap<String, Boolean>> getDictionary() {
        return dictionary;
    }

    /**
     * @param dictionary Hash map value we want to set to the dictionary
     */
    public void setDictionary(HashMap<String, HashMap<String, Boolean>> dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * @param slang Slang word we want to search from our dictionary
     * @return Hashmap contains definitions
     */
    public HashMap<String, HashMap<String, Boolean>> searchBySlang(String slang) throws IOException {
        if (slang.equals("")) return dictionary;
        historyMap.addHistory(slang, false);
        HashMap<String, HashMap<String, Boolean>> res = new HashMap<>();
        HashMap<String, Boolean> slangs = indexSlang.get(slang.toLowerCase());
        if (slangs != null)
            slangs.forEach((key, value) -> {
                res.put(key, dictionary.get(key));
            });
        return res;
    }

    /**
     * @param def Definition we want to search from our dictionary
     * @return Hashmap contains slang words
     */
    public HashMap<String, HashMap<String, Boolean>> searchByDef(String def) throws IOException {
        if (def.equals("")) return dictionary;
        historyMap.addHistory(def, true);
        HashMap<String, HashMap<String, Boolean>> res = new HashMap<>();
        HashMap<String, Boolean> defs = indexDef.get(def.toLowerCase());
        if (defs != null)
            defs.forEach((key, value) -> {
                dictionary.forEach((k, v) -> {
                    if (v.get(key) != null) {
                        res.put(k, v);
                    }
                });
            });
        return res;
    }

    /**
     * @param slang       Slang word we want to add to dictionary
     * @param def         Definition of above slang word
     * @param isOverwrite If slang word exist then if isOverwrite == true then
     *                    the slang word's definition will be overwritten,
     *                    otherwise the definition will be duplicated
     */
    public void addSlangWord(String slang, String def, boolean isOverwrite, boolean isExport) throws IOException {
        addIndexDef(def);
        addIndexSlang(slang);
        HashMap<String, Boolean> defsOfSlang = dictionary.get(slang);
        if (defsOfSlang != null) {
            if (isOverwrite) {
                defsOfSlang = new HashMap<>();
                defsOfSlang.put(def, true);
                dictionary.put(slang, defsOfSlang);
            } else {
                defsOfSlang.put(def, true);
            }
        } else {
            defsOfSlang = new HashMap<>();
            defsOfSlang.put(def, true);
            dictionary.put(slang, defsOfSlang);
        }
        if (isExport) {
            exportIndexData("indexSlang.dat", true);
            exportIndexData("indexDef.dat", false);
            exportToDataFile("data.dat");
        }
    }

    /**
     * @param oldSlang Slang word we want to edit
     * @param newSlang Slang word to replace
     */
    public void editSlang(String oldSlang, String newSlang) throws IOException {
        if (oldSlang.equals(newSlang)) return;
        for (int i = 1; i <= oldSlang.length(); i++) {
            String part = oldSlang.substring(0, i).toLowerCase();
            HashMap<String, Boolean> tmpMap = indexSlang.get(part);
            if (tmpMap != null) {
                tmpMap.remove(oldSlang);
            }
        }
        addIndexSlang(newSlang);
        HashMap<String, Boolean> defsOfSlang = dictionary.get(oldSlang);
        dictionary.remove(oldSlang);
        dictionary.put(newSlang, defsOfSlang);
        exportIndexData("indexSlang.dat", true);
        exportToDataFile("data.dat");
    }

    /**
     * @param slang
     * @param oldDef
     * @param newDef
     * @throws IOException
     */
    public void editDef(String slang, String oldDef, String newDef) throws IOException {
        if (oldDef.equals(newDef)) return;
        String[] parts = oldDef.split(" ");
        for (String part : parts) {
            HashMap<String, Boolean> tmpMap = indexDef.get(part);
            if (tmpMap != null) {
                tmpMap.remove(oldDef);
            }
        }
        addIndexDef(newDef);
        HashMap<String, Boolean> defsOfSlang = dictionary.get(slang);
        if (defsOfSlang != null) {
            defsOfSlang.remove(oldDef);
            defsOfSlang.put(newDef, true);
        }
        exportIndexData("indexDef.dat", false);
        exportToDataFile("data.dat");
    }

    /**
     * @param slang Slang word we want to delete from our dictionary
     */
    public void deleteSlangWord(String slang) throws IOException {
        HashMap<String, Boolean> defs = dictionary.get(slang);
        defs.forEach((def, v) -> {
            for (int i = 1; i <= def.length(); i++) {
                String part = def.substring(0, i).toLowerCase();
                HashMap<String, Boolean> tmpMap = indexDef.get(part);
                if (tmpMap != null) {
                    tmpMap.remove(def);
                }
            }
        });
        for (int i = 1; i <= slang.length(); i++) {
            String part = slang.substring(0, i).toLowerCase();
            HashMap<String, Boolean> tmpMap = indexSlang.get(part);
            if (tmpMap != null) {
                tmpMap.remove(slang);
            }
        }
        dictionary.remove(slang);
        exportToDataFile("data.dat");
    }
}
