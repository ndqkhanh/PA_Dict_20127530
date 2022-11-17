package dictionary;

import java.util.HashMap;
import java.util.HashSet;

/**
 * dictionary
 * Created by khanh
 * Date 11/17/2022 - 7:13 PM
 * Description: ...
 */
public class Dictionary {
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
     * @return a dictionary list with slang words and their definitions
     */
    public HashMap<String, HashSet<String>> getDictionary() {
        return dictionary;
    }

    public void setDictionary(HashMap<String, HashSet<String>> dictionary) {
        this.dictionary = dictionary;
    }
}
