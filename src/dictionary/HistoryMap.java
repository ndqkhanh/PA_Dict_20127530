package dictionary;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * dictionary
 * Created by khanh
 * Date 11/17/2022 - 11:18 PM
 * Description: ...
 */

class SortByDateDescending implements Comparator<History> {
    public int compare(History a, History b) {
        return a.getLdt().compareTo(b.getLdt());
    }
}

class SortByDateAscending implements Comparator<History> {
    public int compare(History a, History b) {
        return b.getLdt().compareTo(a.getLdt());
    }
}

public class HistoryMap {
    private final ArrayList<History> historyMap;

    public HistoryMap() {
        historyMap = new ArrayList<>();
    }

    /**
     * @param searchText
     */
    public void addHistory(String searchText, boolean searchType) {
        for (int i = 0; i < historyMap.size(); i++) {
            if (historyMap.get(i).checkEqual(searchText, searchType)) {
                historyMap.set(i, new History(searchText, searchType));
                return;
            }
        }
    }

    /**
     * @param dateAscending
     */
    public void sortHistory(boolean dateAscending) {
        if (dateAscending)
            this.historyMap.sort(new SortByDateAscending());
        else this.historyMap.sort(new SortByDateDescending());
    }
}
