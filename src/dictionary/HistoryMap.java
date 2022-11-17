package dictionary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * dictionary
 * Created by khanh
 * Date 11/17/2022 - 11:18 PM
 * Description: ...
 */
public class HistoryMap {
    private final HashMap<History, LocalDateTime> historyMap;

    public HistoryMap() {
        historyMap = new HashMap<>();
    }

    public void addHistory(String searchText, boolean searchType) {
        historyMap.put(new History(searchText, searchType), LocalDateTime.now());
    }

    public ArrayList<Map.Entry<History, LocalDateTime>> getHistory(boolean dateAscending) {
        if (dateAscending) {
            return historyMap.entrySet().stream()
                    .sorted(new Comparator<Map.Entry<History, LocalDateTime>>() {
                        @Override
                        public int compare(Map.Entry<History, LocalDateTime> o1, Map.Entry<History, LocalDateTime> o2) {
                            return o1.getValue().compareTo(o2.getValue());
                        }
                    })
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            return historyMap.entrySet().stream()
                    .sorted(new Comparator<Map.Entry<History, LocalDateTime>>() {
                        @Override
                        public int compare(Map.Entry<History, LocalDateTime> o1, Map.Entry<History, LocalDateTime> o2) {
                            return o2.getValue().compareTo(o1.getValue());
                        }
                    })
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }
}
