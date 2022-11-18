package dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * dictionary
 * Created by khanh
 * Date 11/17/2022 - 7:13 PM
 * Description: ...
 */
public class Dictionary {
    private final HistoryMap historyMap = new HistoryMap();
    /**
     * Hash map from slang words to their definitions
     */
    private HashMap<String, HashSet<String>> dictionary;

    /**
     * Default constructor
     */
    public Dictionary() {
        dictionary = new HashMap<>();
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
     * @return ArrayList of slang words and definitions that contain the slang parameter pass in
     */
    public ArrayList<Map.Entry<String, HashSet<String>>> searchDefsBySlang(String slang) {
        return dictionary
                .entrySet()
                .stream()
                .filter(record -> record.getKey().toLowerCase().contains(slang.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @param def Definition we want to search from our dictionary
     * @return ArrayList of slang words and definitions that contain the def parameter pass in
     */
    public ArrayList<Map.Entry<String, HashSet<String>>> searchSlangsByDef(String def) {
        String[] arrOfStr = def.split(" ");
        return dictionary
                .entrySet()
                .stream()
                .filter(record -> {
                    for (String ele : record.getValue())
                        for (String word : arrOfStr)
                            if (ele.equalsIgnoreCase(word)) return true;
                    return false;
                }).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @param slang       Slang word we want to add to dictionary
     * @param def         Definition of above slang word
     * @param isOverwrite If slang word exist then if isOverwrite == true then
     *                    the slang word's definition will be overwritten,
     *                    otherwise the definition will be duplicated
     */
    public void addSlangWord(String slang, String def, boolean isOverwrite) {
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
    }

    /**
     * @param slang  Slang word we want to edit
     * @param oldDef Old definition of slang word
     * @param newDef New definition of slang word we want to update
     */
    public void editSlangWord(String slang, String oldDef, String newDef) {
        HashSet<String> defsOfSlang = dictionary.get(slang);
        boolean result = defsOfSlang.remove(oldDef);
        if (result) defsOfSlang.add(newDef);
    }

    /**
     * @param slang Slang word we want to delete from our dictionary
     */
    public void deleteSlangWord(String slang) {
        dictionary.remove(slang);
    }

    public void resetDictionary(HashMap<String, HashSet<String>> dictionary) {
        dictionary.clear();
        this.dictionary = dictionary;
    }
}
