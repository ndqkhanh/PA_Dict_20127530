package dictionary;

import java.io.*;
import java.time.LocalDateTime;
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
    private ArrayList<History> historyMap;

    public HistoryMap() {
        historyMap = new ArrayList<>();
    }

    public ArrayList<History> getHistoryMap() {
        return historyMap;
    }

    public void importFromFile(String filename) throws IOException {
        historyMap = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        while (true) {
            str = br.readLine();
            if (str == null)
                break;
            String[] tmp = str.split(",");
            if (tmp.length == 3) {
                historyMap.add(new History(tmp[0], Boolean.parseBoolean(tmp[1]), LocalDateTime.parse(tmp[2])));
            }
        }
        br.close();
    }

    public void exportToFile(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for (History h : historyMap) {
            bw.write(h.getHistorySearchText() + "," + h.getSearchType() + "," + h.getLdt() + "\n");
        }
        bw.flush();
        bw.close();
    }

    /**
     * @param searchText
     */
    public void addHistory(String searchText, boolean searchType) throws IOException {
        for (History h : historyMap) {
            if (h.checkEqual(searchText, searchType)) {
                h.setHistory(searchText, searchType);
                return;
            }
        }
        historyMap.add(new History(searchText, searchType));
        exportToFile("history.dat");
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
