package dictionary;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * dictionary
 * Created by khanh
 * Date 11/17/2022 - 7:13 PM
 * Description: ...
 */
public class Dictionary {
    private final HistoryMap historyMap = new HistoryMap();
    private final HashMap<String, HashMap<String, Boolean>> indexSlang;
    private final HashMap<String, HashMap<String, Boolean>> indexDef;
    private HashMap<String, HashSet<String>> dictionary;

    /**
     * Default constructor
     */
    public Dictionary() {
        indexSlang = new HashMap<>();
        indexDef = new HashMap<>();
        dictionary = new HashMap<>();
    }

    public void addIndexData(String word, boolean dataType) {
        for (int i = 1; i <= word.length(); i++) {
            String part = word.substring(0, i).toLowerCase();
            HashMap<String, Boolean> tmpMap = dataType ? indexDef.get(part) : indexSlang.get(part);
            if (tmpMap != null) {
                tmpMap.putIfAbsent(word, true);
            } else {
                tmpMap = new HashMap<>();
                tmpMap.put(word, true);
                if (dataType)
                    indexDef.put(part, tmpMap);
                else indexSlang.put(part, tmpMap);
            }
        }
    }

    public void importFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        while (true) {
            str = br.readLine();
            if (str == null)
                break;
            String[] slangAndDef = str.split("`");
            String[] defArray = slangAndDef[1].split("(\\| )");
            addIndexData(slangAndDef[0], false);
            for (String def : defArray) {
                addIndexData(def, true);
                addSlangWord(slangAndDef[0], def, false, false);
            }
        }
        exportToDataFile(filename);
        historyMap.importFromFile("history.dat");
    }

    public void exportToDataFile(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        dictionary
                .forEach((key, value) -> {
                    try {
                        StringBuilder message = new StringBuilder();
                        value.forEach(v -> {
                            message.append(v).append("| ");
                        });
                        bw.write(key + "`" + message.substring(0, message.length() - 2) + "\n");
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });
        bw.flush();
        bw.close();
    }

    public HashMap<String, HashSet<String>> generateRandom() {
        HashMap<String, HashSet<String>> res = new HashMap<>();
        Object[] crunchifyKeys = dictionary.keySet().toArray();
        Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
        res.put(key.toString(), dictionary.get(key.toString()));
        return res;
    }

    /**
     * @return A dictionary list with slang words and their definitions
     */
    public HashMap<String, HashSet<String>> getDictionary() {
        return dictionary;
    }

    /**
     * @param dictionary Hash map value we want to set to the dictionary
     */
    public void setDictionary(HashMap<String, HashSet<String>> dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * @param slang Slang word we want to search from our dictionary
     * @return Hashmap contains definitions
     */
    public HashMap<String, Boolean> searchDefsBySlang(String slang) throws IOException {
        historyMap.addHistory(slang, false);
        historyMap.exportToFile("history.dat");
        return indexSlang.get(slang.toLowerCase());
    }

    /**
     * @param def Definition we want to search from our dictionary
     * @return Hashmap contains slang words
     */
    public HashMap<String, Boolean> searchSlangsByDef(String def) throws IOException {
        historyMap.addHistory(def, true);
        historyMap.exportToFile("history.dat");
        return indexDef.get(def.toLowerCase());
    }

    /**
     * @param slang       Slang word we want to add to dictionary
     * @param def         Definition of above slang word
     * @param isOverwrite If slang word exist then if isOverwrite == true then
     *                    the slang word's definition will be overwritten,
     *                    otherwise the definition will be duplicated
     */
    public void addSlangWord(String slang, String def, boolean isOverwrite, boolean isExport) throws IOException {
        addIndexData(def, false);
        addIndexData(slang, true);
        HashSet<String> defsOfSlang = dictionary.get(slang);
        if (defsOfSlang != null) {
            if (isOverwrite) {
                defsOfSlang = new HashSet<>();
                defsOfSlang.add(def);
                dictionary.put(slang, defsOfSlang);
            } else {
                defsOfSlang.add(def);
            }
        } else {
            defsOfSlang = new HashSet<>();
            defsOfSlang.add(def);
            dictionary.put(slang, defsOfSlang);
        }
        if (isExport) exportToDataFile("slang.txt");
    }

    /**
     * @param oldSlang Slang word we want to edit
     * @param newSlang Slang word to replace
     * @param oldDef   Old definition of slang word
     * @param newDef   New definition of slang word we want to update
     */
    public void editSlangWord(String oldSlang, String newSlang, String oldDef, String newDef) throws IOException {
        for (int i = 1; i <= oldDef.length(); i++) {
            String part = oldDef.substring(0, i).toLowerCase();
            HashMap<String, Boolean> tmpMap = indexDef.get(part);
            if (tmpMap != null) {
                tmpMap.remove(oldDef);
            }
        }
        for (int i = 1; i <= oldSlang.length(); i++) {
            String part = oldSlang.substring(0, i).toLowerCase();
            HashMap<String, Boolean> tmpMap = indexSlang.get(part);
            if (tmpMap != null) {
                tmpMap.remove(oldSlang);
            }
        }
        addIndexData(newDef, false);
        addIndexData(newSlang, true);

        HashSet<String> defsOfSlang = dictionary.get(oldSlang);
        if (oldSlang.equals(newSlang)) {
            boolean result = defsOfSlang.remove(oldDef);
            if (result) defsOfSlang.add(newDef);
        } else {
            dictionary.remove(oldSlang);
            defsOfSlang = new HashSet<>();
            defsOfSlang.add(newDef);
            dictionary.put(newSlang, defsOfSlang);
        }
        exportToDataFile("slang.txt");
    }

    /**
     * @param slang Slang word we want to delete from our dictionary
     */
    public void deleteSlangWord(String slang) throws IOException {
        HashSet<String> defs = dictionary.get(slang);
        defs.forEach(def -> {
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
        exportToDataFile("slang.txt");
    }
}
