package dictionary;

import java.io.*;
import java.util.*;

/**
 * dictionary
 * Created by khanh
 * Date 11/17/2022 - 7:13 PM
 * Description: ...
 */
public class Dictionary {
    private HashMap<String, HashMap<String, Boolean>> historyMap;
    private HashMap<String, HashMap<String, Boolean>> indexSlang;
    private HashMap<String, HashMap<String, Boolean>> dictionary;

    /**
     * Default constructor
     */
    public Dictionary() {
        historyMap = new HashMap<>();
        indexSlang = new HashMap<>();
        dictionary = new HashMap<>();
    }

    public static String[][] convertMapToList(HashMap<String, HashMap<String, Boolean>> map) {
        ArrayList<String[]> mainList = new ArrayList<>();
        map.forEach((key, value) -> value.forEach((k, v) -> {
            String[] tmp = {key, k};
            mainList.add(tmp);
        }));
        String[][] resList = new String[mainList.size()][];
        final int[] i = {0};
        mainList.forEach(line -> {
            resList[i[0]] = line;
            i[0] = i[0] + 1;
        });
        return resList;
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

    public void importIndexData(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        while (true) {
            str = br.readLine();
            if (str == null)
                break;
            String[] parts = str.split("`", 2);
            String[] values = parts[1].split("`");
            HashMap<String, Boolean> tmpMap = new HashMap<>();
            for (String v : values)
                tmpMap.put(v, true);
            indexSlang.put(parts[0], tmpMap);
        }
        br.close();
    }

    public void exportIndexData(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        indexSlang.forEach((key, value) -> {
            try {
                StringBuilder message = new StringBuilder();
                if (!value.isEmpty()) {
                    value.forEach((k, v) -> message.append(k).append("`"));
                    bw.write(key + "`" + message.substring(0, message.length() - 1) + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        bw.flush();
        bw.close();
    }

    /**
     * @param filename        name of file
     * @param importIndexFile check if import index file or not
     * @throws IOException throw io exception
     */
    public void importFromFile(String filename, boolean importIndexFile) throws IOException {
        dictionary = new HashMap<>();
        indexSlang = new HashMap<>();
        historyMap = new HashMap<>();
        if (importIndexFile) {
            importIndexData("indexSlang.dat");
        }
        File f = new File("history.dat");
        if (f.exists()) {
            importFromHistoryFile("history.dat");
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
                addSlangWord(slangAndDef[0], def, false, false);
            }
        }
        if (filename.equals("slang.txt")) {
            exportToDataFile("data.dat");
            exportToHistoryFile("history.dat");
        }
        if (!importIndexFile) {
            exportIndexData("indexSlang.dat");
        }
    }


    public void importFromHistoryFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        while (true) {
            str = br.readLine();
            if (str == null)
                break;
            String[] slangAndDef = str.split("`");
            String[] defArray = slangAndDef[1].split("(\\| )");
            for (String def : defArray) {
                HashMap<String, Boolean> defsOfSlang = historyMap.get(slangAndDef[0]);
                if (defsOfSlang != null) {
                    defsOfSlang.put(def, true);
                } else {
                    defsOfSlang = new HashMap<>();
                    defsOfSlang.put(def, true);
                    historyMap.put(slangAndDef[0], defsOfSlang);
                }
            }
        }
    }

    public void exportToDataFile(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        dictionary
                .forEach((key, value) -> {
                    try {
                        StringBuilder message = new StringBuilder();
                        value.forEach((k, v) -> message.append(k).append("| "));
                        bw.write(key + "`" + message.substring(0, message.length() - 2) + "\n");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                });
        bw.flush();
        bw.close();
    }

    public void exportToHistoryFile(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        historyMap
                .forEach((key, value) -> {
                    try {
                        StringBuilder message = new StringBuilder();
                        value.forEach((k, v) -> message.append(k).append("| "));
                        bw.write(key + "`" + message.substring(0, message.length() - 2) + "\n");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                });
        bw.flush();
        bw.close();
    }

    /**
     * @return HashMap<String, HashMap < String, Boolean>> random slang word
     */
    public HashMap<String, HashMap<String, Boolean>> generateRandom() {
        HashMap<String, HashMap<String, Boolean>> res = new HashMap<>();
        Object[] crunchifyKeys = dictionary.keySet().toArray();
        Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
        res.put(key.toString(), dictionary.get(key.toString()));
        return res;
    }

    public ArrayList<String> generateNewQuiz(HashMap<String, HashMap<String, Boolean>> quiz, boolean isSlangType) {
        ArrayList<String> res = new ArrayList<>();
        final int[] i = {0};

        if (isSlangType) {
            final int[] t = {0};
            quiz.values().forEach(e -> {
                if (t[0] == 0) {
                    Optional<String> tmp = e.keySet().stream().findFirst();
                    tmp.ifPresent(res::add);
                }
                t[0] += 1;
            });
            dictionary.forEach((key, value) -> {
                if (i[0] < 3 && quiz.get(key) == null) {
                    Optional<String> tmp = value.keySet().stream().findFirst();
                    tmp.ifPresent(res::add);
                    i[0] += 1;
                }
            });
        } else {
            Optional<String> tmp = quiz.keySet().stream().findFirst();
            tmp.ifPresent(res::add);
            dictionary.forEach((key, value) -> {
                if (i[0] < 3 && quiz.get(key) == null) {
                    res.add(key);
                    i[0] += 1;
                }
            });
        }
        Collections.shuffle(res);
        return res;
    }

    public HashMap<String, HashMap<String, Boolean>> getHistoryMap() {
        return historyMap;
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
        HashMap<String, HashMap<String, Boolean>> res = new HashMap<>();
        HashMap<String, Boolean> slangs = indexSlang.get(slang.toLowerCase());
        if (slangs != null)
            slangs.forEach((key, value) -> res.put(key, dictionary.get(key)));
        historyMap.putAll(res);
        exportToHistoryFile("history.dat");
        return res;
    }

    /**
     * @param def Definition we want to search from our dictionary
     * @return Hashmap contains slang words
     */
    public HashMap<String, HashMap<String, Boolean>> searchByDef(String def) throws IOException {
        HashMap<String, HashMap<String, Boolean>> res = new HashMap<>();
        dictionary.forEach((key, value) -> value.keySet().forEach(k -> {
            if (k.toLowerCase().contains(def.toLowerCase()))
                res.put(key, value);
        }));
        historyMap.putAll(res);
        exportToHistoryFile("history.dat");
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
            exportIndexData("indexSlang.dat");
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
                if (tmpMap.isEmpty()) {
                    indexSlang.remove(part);
                }
            }
        }
        addIndexSlang(newSlang);
        HashMap<String, Boolean> defsOfSlang = dictionary.get(oldSlang);
        dictionary.remove(oldSlang);
        dictionary.put(newSlang, defsOfSlang);
        exportIndexData("indexSlang.dat");
        exportToDataFile("data.dat");
    }

    /**
     *
     */
    public void editDef(String slang, String oldDef, String newDef) throws IOException {
        if (oldDef.equals(newDef)) return;
        HashMap<String, Boolean> defsOfSlang = dictionary.get(slang);
        if (defsOfSlang != null) {
            defsOfSlang.remove(oldDef);
            defsOfSlang.put(newDef, true);
        }
        exportIndexData("indexDef.dat");
        exportToDataFile("data.dat");
    }

    /**
     * @param slang Slang word we want to delete from our dictionary
     */
    public void deleteSlangWord(String slang) throws IOException {
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
