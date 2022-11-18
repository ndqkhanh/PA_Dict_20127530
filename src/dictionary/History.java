package dictionary;

import java.time.LocalDateTime;

/**
 * dictionary
 * Created by khanh
 * Date 11/17/2022 - 11:08 PM
 * Description: ...
 */
public class History {
    // Searched text
    private String historySearchText;
    // 0: search by slang, 1: search by definition
    private boolean searchType;
    // Date & time search
    private LocalDateTime ldt;

    public History() {
        ldt = LocalDateTime.now();
        historySearchText = "";
        searchType = false;
    }

    public History(String historySearchText, boolean searchType) {
        this.historySearchText = historySearchText;
        this.searchType = searchType;
        ldt = LocalDateTime.now();
    }

    public String getHistorySearchText() {
        return historySearchText;
    }

    public boolean getSearchType() {
        return searchType;
    }

    public void setHistory(String historySearchText, boolean searchType) {
        this.historySearchText = historySearchText;
        this.searchType = searchType;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public boolean checkEqual(String historySearchText, boolean searchType) {
        return this.searchType == searchType && this.historySearchText.equals(historySearchText);
    }
}
